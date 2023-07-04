package br.com.watchwatt.watchwatt.domain.appliance;

import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@SequenceGenerator(name = "appliance_sequence", initialValue = 11)
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "appliance_sequence")
    Long id;
    String name;
    String model;
    Integer power;

    public Appliance(ApplianceDTO applianceDTO) {
        this.name = applianceDTO.name();
        this.model = applianceDTO.model();
        this.power = applianceDTO.power();
    }

    public void update(ApplianceUpdateDTO applianceUpdateDTO) {
        if (applianceUpdateDTO.name() != null) {
            this.name = applianceUpdateDTO.name();
        }

        if (applianceUpdateDTO.model() != null) {
            this.model = applianceUpdateDTO.model();
        }

        if (applianceUpdateDTO.power() != null) {
            this.power = applianceUpdateDTO.power();
        }
    }
}
