package br.com.watchwatt.watchwatt.domain.appliance;

import br.com.watchwatt.watchwatt.domain.user.User;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Appliance(ApplianceDTO applianceDTO, User user) {
        this.name = applianceDTO.name();
        this.model = applianceDTO.model();
        this.power = applianceDTO.power();
        this.user = user;
    }

    public Appliance(Appliance appliance) {
        this.name = appliance.getName();
        this.model = appliance.getModel();
        this.power = appliance.getPower();
    }

    public Appliance(ApplianceDTO appliance) {
        this.name = appliance.name();
        this.model = appliance.model();
        this.power = appliance.power();
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
