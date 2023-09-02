package br.com.watchwatt.watchwatt.domain.address;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.domain.kinship.Kinship;
import br.com.watchwatt.watchwatt.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static java.util.Collections.emptyList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_address")
@SequenceGenerator(name = "address_sequence", initialValue = 11)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "address_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "address", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Appliance> appliances = emptyList();

    @OneToMany(mappedBy = "address", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Kinship> kinships = emptyList();

    private String zipCode;
    private String street;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;
    private String reference;
}