package br.com.watchwatt.watchwatt.service.address;

import br.com.watchwatt.watchwatt.dao.address.AddressRepository;
import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.domain.user.User;
import br.com.watchwatt.watchwatt.dto.address.AddressDTO;
import br.com.watchwatt.watchwatt.dto.address.viacep.ViaCepAddressDTO;
import br.com.watchwatt.watchwatt.dto.address.viacep.ViaCepDTO;
import br.com.watchwatt.watchwatt.dto.user.UserDTO;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.gateway.viacep.ViaCepGateway;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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

  public List<Address> getAddresses() {
    return repository.findAll();
  }

  private AddressDTO createAddressDTO(ViaCepDTO viaCep, ViaCepAddressDTO viaCepAddressDTO) {
    return new AddressDTO(
            viaCep.zipCode(),
            viaCep.street(),
            viaCepAddressDTO.number(),
            viaCep.neighborhood(),
            viaCep.city(),
            viaCep.state(),
            viaCepAddressDTO.reference()
    );
  }

  private void validateRegisteredAddress(Optional<Address> address) {
    address.ifPresent(it -> {
      throw new BadRequestException(ADDRESS_MESSAGE);
    });
  }

  private Optional<Address> findByZipCodeAndCityAndNumberAndNeighborhood(AddressDTO addressDTO) {
    return repository.findByZipCodeAndCityAndNumberAndNeighborhood(
            addressDTO.zipCode(),
            addressDTO.city(),
            addressDTO.number(),
            addressDTO.neighborhood());
  }

  public Address updateAddress(Long id, AddressDTO addressDTO) {
    var address = repository.findById(id).orElseThrow(() -> new NotFoundException(ADDRESS_NOT_FOUND));
    var updatedAddress = new Address(address.getId(), addressDTO.zipCode(), addressDTO.street(), addressDTO.number(), addressDTO.neighborhood(),
            addressDTO.city(), addressDTO.state(), addressDTO.reference());

    return repository.save(updatedAddress);
  }

  public void delete(Long id) {
    var address = repository.findById(id).orElseThrow(() -> new NotFoundException(ADDRESS_NOT_FOUND));
    repository.delete(address);

  }
}