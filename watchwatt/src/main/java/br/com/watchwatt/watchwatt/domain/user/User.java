package br.com.watchwatt.watchwatt.domain.user;

import br.com.watchwatt.watchwatt.domain.kinship.Kinship;
import br.com.watchwatt.watchwatt.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import static java.util.Collections.emptyList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tb_user")
@SequenceGenerator(name = "user_sequence", initialValue = 11)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @JsonIgnore
    @Column(unique = true)
    private String cpf;

    private String name;

    private LocalDate birthday;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private ZonedDateTime dateCreated = ZonedDateTime.now();

    private ZonedDateTime updateDate = ZonedDateTime.now();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Kinship> kinship = emptyList();

    public User(UserDTO userDTO) {
        this.cpf = userDTO.cpf();
        this.name = userDTO.name();
        this.birthday = userDTO.birthday();
        this.gender = userDTO.gender();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.kinship = userDTO.getKinship();
    }
}
