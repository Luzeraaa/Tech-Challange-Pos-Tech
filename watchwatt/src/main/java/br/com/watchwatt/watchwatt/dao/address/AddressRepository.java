package br.com.watchwatt.watchwatt.dao.address;

import br.com.watchwatt.watchwatt.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  Optional<Address> findByZipCodeAndCityAndNumberAndNeighborhood(
          String zipCode, String city, Integer number, String Neighborhood);

  Optional<Address> findByZipCodeAndCityAndNumberAndNeighborhoodAndUserCpf(
          String zipCode, String city, Integer number, String Neighborhood, String cpf);

  Optional<Address> findByIdAndUserId(long id, Long userId);

  @Query("select a from Address a where a.zipCode = ?1")
  List<Address> findAddressByZipCode(String zipCode);

  @Query("select a from Address a where a.street = ?1")
  List<Address> findAddressByStreet(String street);

  @Query("select a from Address a where a.number = ?1")
  List<Address> findAddressByNumber(Integer number);

  @Query("select a from Address a where a.neighborhood = ?1")
  List<Address> findAddressByNeighborhood(String neighborhood);

  @Query("select a from Address a where a.city = ?1")
  List<Address> findAddressByCity(String city);

  @Query("select a from Address a where a.state = ?1")
  List<Address> findAddressByState(String state);
}
