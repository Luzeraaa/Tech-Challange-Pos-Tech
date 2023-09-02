package br.com.watchwatt.watchwatt.domain.kinship;

import br.com.watchwatt.watchwatt.domain.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tb_kinship")
@SequenceGenerator(name = "kinship_sequence", initialValue = 11)
public class Kinship {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kinship_sequence")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DegreeKinship degreeKinship;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;

    public Kinship(String name, DegreeKinship degreeKinship, Address address) {
        this.name = name;
        this.degreeKinship = degreeKinship;
        this.address = address;
    }

    public Kinship(String name, DegreeKinship degreeKinshipVector) {
        this.name = name;
        this.degreeKinship = degreeKinshipVector;
    }
}
