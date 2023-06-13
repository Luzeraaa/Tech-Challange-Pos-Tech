package br.com.watchwatt.watchwatt.domain.user;

import br.com.watchwatt.watchwatt.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tb_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @JsonIgnore
  @Column(unique = true)
  private String cpf;
  private String name;
  private LocalDate birthday;
  private String email;
  @JsonIgnore
  private String password;
  @Enumerated(EnumType.STRING)
  private Gender gender;
  private ZonedDateTime dateCreated = ZonedDateTime.now();
  private ZonedDateTime updateDate = ZonedDateTime.now();

  public User(UserDTO userDTO) {
    this.cpf = userDTO.cpf();
    this.name = userDTO.name();
    this.birthday = userDTO.birthday();
    this.gender = userDTO.gender();
    this.email = userDTO.email();
    this.password = userDTO.password();
  }
}
