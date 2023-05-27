package br.com.watchwatt.watchwatt.dao.address;

import br.com.watchwatt.watchwatt.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  Optional<Address> findByZipCodeAndCityAndNumber(String zipCode, String city, Integer number);
  // SELECT * FROM TB_ADDRESS WHERE ZIPCODE=123 AND CITY="SP" AND NUMER=10
}
