package br.com.watchwatt.watchwatt.domain.address;

import br.com.watchwatt.watchwatt.dto.address.AddressUpdateDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String zipCode;
  private String street;
  private Integer number;
  private String neighborhood;
  private String city;
  private String state;
  private String reference;

  public void atualizar(AddressUpdateDTO addressUpdateDTO){
    if(addressUpdateDTO.zipCode() != null) {
      this.zipCode = addressUpdateDTO.zipCode();
    }

    if(addressUpdateDTO.street() != null){
      this.street = addressUpdateDTO.street();
    }

    if(addressUpdateDTO.number() != null){
      this.number = addressUpdateDTO.number();
    }

    if(addressUpdateDTO.neighborhood() != null){
      this.neighborhood = addressUpdateDTO.neighborhood();
    }

    if(addressUpdateDTO.city() != null) {
      this.city = addressUpdateDTO.city();
    }

    if(addressUpdateDTO.state() != null){
      this.state = addressUpdateDTO.state();
    }

    if(addressUpdateDTO.reference() != null) {
      this.reference = addressUpdateDTO.reference();
    }
  }
}