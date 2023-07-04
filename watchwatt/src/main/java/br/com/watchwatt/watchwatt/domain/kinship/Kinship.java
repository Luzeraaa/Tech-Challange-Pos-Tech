package br.com.watchwatt.watchwatt.domain.kinship;

import br.com.watchwatt.watchwatt.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonIgnore
    private User user;

    public Kinship(String name, DegreeKinship degreeKinship, User user) {
        this.name = name;
        this.degreeKinship = degreeKinship;
        this.user = user;
    }

    public Kinship(String name, DegreeKinship degreeKinshipVector) {
        this.name = name;
        this.degreeKinship = degreeKinshipVector;
    }
}
