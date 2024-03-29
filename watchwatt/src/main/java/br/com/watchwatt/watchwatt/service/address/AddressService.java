package br.com.watchwatt.watchwatt.service.address;

import br.com.watchwatt.watchwatt.dao.address.AddressRepository;
import br.com.watchwatt.watchwatt.dao.user.UserRepository;
import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.dto.address.AddressDTO;
import br.com.watchwatt.watchwatt.dto.address.AddressUpdateDTO;
import br.com.watchwatt.watchwatt.dto.address.viacep.ViaCepAddressDTO;
import br.com.watchwatt.watchwatt.dto.address.viacep.ViaCepDTO;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.exception.ResourceAlreadyExistsException;
import br.com.watchwatt.watchwatt.gateway.viacep.ViaCepGateway;
import br.com.watchwatt.watchwatt.util.Pagination;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class AddressService {
    private static final String ADDRESS_MESSAGE = "Address already registered";
    private static final String ID_NOT_FUND = "ID: %s not found";
    private static final String USER_NOT_FUND = "User not found";
    private static final String ADDRESS_NOT_FOUND = "Address: %s not found";
    private final AddressRepository repository;
    private final ViaCepGateway gateway;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public AddressService(AddressRepository repository, ViaCepGateway gateway, UserRepository userRepository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.gateway = gateway;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Address registerAddress(AddressDTO addressDTO, @NotNull Authentication auth) {
        var user = userRepository.findByCpfOrEmail(null, auth.getName())
                .orElseThrow(() -> new BadRequestException(USER_NOT_FUND));

        var address = objectMapper.convertValue(addressDTO, Address.class);

        repository.findByZipCodeAndCityAndNumberAndNeighborhoodAndUserCpf(
                        address.getZipCode(),
                        address.getCity(),
                        address.getNumber(),
                        address.getNeighborhood(),
                        user.getCpf())
                .ifPresent(it -> {
                    throw new ResourceAlreadyExistsException(ADDRESS_MESSAGE);
                });

        address.setUser(user);
        user.getAddresses().add(address);

        return repository.save(address);
    }

    public Address registerAddressViaCep(ViaCepAddressDTO viaCepAddressDTO, Authentication auth) {
        var user = userRepository.findByCpfOrEmail(null, auth.getName())
                .orElseThrow(() -> new BadRequestException(USER_NOT_FUND));

        var viaCep = gateway.getCep(viaCepAddressDTO.zipCode());
        var addressDTO = createAddressDTO(viaCep, viaCepAddressDTO);

        var address = repository.findByZipCodeAndCityAndNumberAndNeighborhood(
                addressDTO.zipCode(),
                addressDTO.city(),
                addressDTO.number(),
                addressDTO.neighborhood());

        validateRegisteredAddress(address);

        address.ifPresent(it -> {
            it.setUser(user);
            user.getAddresses().add(it);
        });

        return repository.save(addressDTO.getAddress(user));
    }


    public Address getAddressById(long id) {
        var address = repository.findById(id);

        return address.orElseThrow(() -> new NotFoundException(format(ID_NOT_FUND, id)));
    }

    public Pagination<Address> getAddresses(Integer limit, Integer offset) {
        var pageRequest = PageRequest.of(offset, limit);
        var addressPagination = repository.findAll(pageRequest);

        return new Pagination<>(addressPagination);
    }

    private AddressDTO createAddressDTO(ViaCepDTO viaCep, ViaCepAddressDTO viaCepAddressDTO) {
        return new AddressDTO(viaCep.zipCode(), viaCep.street(), viaCepAddressDTO.number(), viaCep.neighborhood(),
                viaCep.city(), viaCep.state(), viaCepAddressDTO.reference());
    }

    private void validateRegisteredAddress(Optional<Address> address) {
        address.ifPresent(it -> {
            throw new BadRequestException(ADDRESS_MESSAGE);
        });
    }

    @Transactional
    public void update(Long id, @Valid AddressUpdateDTO dto) {
        var viaCep = gateway.getCep(dto.zipCode());

        repository.findById(id).ifPresentOrElse(
                it -> {
                    it.setZipCode(viaCep.zipCode());
                    it.setStreet(viaCep.street());
                    it.setNeighborhood(viaCep.neighborhood());
                    it.setCity(viaCep.city());
                    it.setState(viaCep.state());
                    it.setNumber(dto.number());
                    it.setReference(dto.reference());
                }, () -> {
                    throw new NotFoundException(format(ADDRESS_NOT_FOUND, id));
                }
        );
    }

    public void delete(Long id) {
        var address = repository.findById(id).orElseThrow(() -> new NotFoundException(ADDRESS_NOT_FOUND));
        repository.delete(address);
    }

    public List<Address> getAddressByZipCode(String zipcode) {
        var address = repository.findAddressByZipCode(zipcode);

        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, zipcode));
        }
        return address;
    }

    public List<Address> getAddressByStreet(String street) {
        var address = repository.findAddressByStreet(street);

        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }
        return address;
    }

    public List<Address> getAddressByNumber(Integer number) {
        var address = repository.findAddressByNumber(number);

        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }

        return address;
    }

    public List<Address> getAddressByNeighborhood(String neighborhood) {
        var address = repository.findAddressByNeighborhood(neighborhood);

        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }

        return address;
    }

    public List<Address> getAddressByCity(String city) {
        var address = repository.findAddressByCity(city);

        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }

        return address;
    }

    public List<Address> getAddressByState(String state) {
        var address = repository.findAddressByState(state);

        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }

        return address;
    }
}
