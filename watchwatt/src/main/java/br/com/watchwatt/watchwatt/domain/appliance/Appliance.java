package br.com.watchwatt.watchwatt.domain.appliance;

import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_appliance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "appliance_sequence", initialValue = 11)
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "appliance_sequence")
    Long id;
    String name;
    String model;
    Integer power;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;

    public Appliance(ApplianceDTO applianceDTO, Address address) {
        this.name = applianceDTO.name();
        this.model = applianceDTO.model();
        this.power = applianceDTO.power();
        this.address = address;
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
