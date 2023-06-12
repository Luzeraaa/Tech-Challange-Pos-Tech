package br.com.watchwatt.watchwatt.domain.appliance;

import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_appliance")
@EqualsAndHashCode
public class Appliance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String name;
  String model;
  int power; //potência do equipamento

  public Appliance(ApplianceDTO applianceDTO) {
    this.name = applianceDTO.name();
    this.model = applianceDTO.model();
    this.power = applianceDTO.power();
  }
}
