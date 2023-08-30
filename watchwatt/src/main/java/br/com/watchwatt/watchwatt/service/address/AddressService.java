package br.com.watchwatt.watchwatt.service.address;

import br.com.watchwatt.watchwatt.dao.address.AddressRepository;
import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.dto.address.AddressDTO;
import br.com.watchwatt.watchwatt.dto.address.AddressUpdateDTO;
import br.com.watchwatt.watchwatt.dto.address.viacep.ViaCepAddressDTO;
import br.com.watchwatt.watchwatt.dto.address.viacep.ViaCepDTO;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.gateway.viacep.ViaCepGateway;
import br.com.watchwatt.watchwatt.util.Pagination;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class AddressService {
    private final AddressRepository repository;
    private final ViaCepGateway gateway;
    private static final String ADDRESS_MESSAGE = "Address already registered";
    private static final String ID_NOT_FUND = "ID: %s not found";
    private static final String ADDRESS_NOT_FOUND = "Address not found";

    public AddressService(AddressRepository repository, ViaCepGateway gateway) {
        this.repository = repository;
        this.gateway = gateway;
    }

    public Address registerAddress(AddressDTO addressDTO) {
        var address = findByZipCodeAndCityAndNumberAndNeighborhood(addressDTO);
        validateRegisteredAddress(address);

        return repository.save(addressDTO.getAddress());
    }

    public Address registerAddressViaCep(ViaCepAddressDTO viaCepAddressDTO) {
        var viaCep = gateway.getCep(viaCepAddressDTO.zipCode());
        var addressDTO = createAddressDTO(viaCep, viaCepAddressDTO);
        var address = findByZipCodeAndCityAndNumberAndNeighborhood(addressDTO);
        validateRegisteredAddress(address);

        return repository.save(addressDTO.getAddress());
    }

    public Optional<Address> getAddressById(long id) {
        var address = repository.findById(id);

        if (address.isEmpty()) {
            throw new NotFoundException(format(ID_NOT_FUND, id));
        }

        return address;
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

    private Optional<Address> findByZipCodeAndCityAndNumberAndNeighborhood(AddressDTO addressDTO) {
        return repository.findByZipCodeAndCityAndNumberAndNeighborhood(addressDTO.zipCode(), addressDTO.city(),
                addressDTO.number(), addressDTO.neighborhood());
    }

    @Transactional
    public void update(Long id, @Valid AddressUpdateDTO dto) {
        try {
            var viaCep = gateway.getCep(dto.zipCode());
            var address = repository.getReferenceById(id);

            address.setZipCode(viaCep.zipCode());
            address.setStreet(viaCep.street());
            address.setNeighborhood(viaCep.neighborhood());
            address.setCity(viaCep.city());
            address.setState(viaCep.state());
            address.setNumber(dto.number());
            address.setReference(dto.reference());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ADDRESS_NOT_FOUND);
        }
    }

    public void delete(Long id) {
        var address = repository.findById(id).orElseThrow(() -> new NotFoundException(ADDRESS_NOT_FOUND));
        repository.delete(address);
    }

    public List<Address> getAddressByZipCode(String zipcode){
        var address = repository.findAddressByZipCode(zipcode);
        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }
        return address;
    }

    public List<Address> getAddressByStreet(String street){
        var address = repository.findAddressByStreet(street);
        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }
        return address;
    }

    public List<Address> getAddressByNumber(Integer number){
        var address = repository.findAddressByNumber(number);
        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }
        return address;
    }

    public List<Address> getAddressByNeighborhood(String neighborhood){
        var address = repository.findAddressByNeighborhood(neighborhood);
        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }
        return address;
    }

    public List<Address> getAddressByCity(String city){
        var address = repository.findAddressByCity(city);
        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }
        return address;
    }

    public List<Address> getAddressByState(String state){
        var address = repository.findAddressByState(state);
        if (address.isEmpty()) {
            throw new NotFoundException(format(ADDRESS_NOT_FOUND, address));
        }
        return address;
    }
}