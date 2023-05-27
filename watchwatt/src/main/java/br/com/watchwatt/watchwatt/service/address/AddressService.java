package br.com.watchwatt.watchwatt.service.address;

import br.com.watchwatt.watchwatt.dao.address.AddressRepository;
import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.dto.address.AddressDTO;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AddressService {


  private final AddressRepository repository;
  private static final String ADDRESS_MESSAGE = "address already registered";

  public AddressService(AddressRepository repository) {
    this.repository = repository;
  }

  @Transactional()
  public Address registerAddress(AddressDTO addressDTO) {
    var address = repository.findByZipCodeAndCityAndNumber(
            addressDTO.zipCode(),
            addressDTO.city(),
            addressDTO.number());

    address.ifPresent(it -> {
      throw new BadRequestException(ADDRESS_MESSAGE);
    });

    return repository.save(addressDTO.getAddress());
  }
}
