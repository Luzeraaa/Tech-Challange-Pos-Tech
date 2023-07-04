package br.com.watchwatt.watchwatt.domain.address;

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
import lombok.Setter;

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

    private String zipCode;
    private String street;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;
    private String reference;
}