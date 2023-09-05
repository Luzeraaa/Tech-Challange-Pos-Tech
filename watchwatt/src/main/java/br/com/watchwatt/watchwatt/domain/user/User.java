package br.com.watchwatt.watchwatt.domain.user;

import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.dto.user.UserDTO;
import br.com.watchwatt.watchwatt.dto.user.UserUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SequenceGenerator(name = "user_sequence", initialValue = 11)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

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

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime updateDate;


    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses = emptyList();

    public User(UserDTO userDTO) {
        this.cpf = userDTO.cpf();
        this.name = userDTO.name();
        this.birthday = userDTO.birthday();
        this.gender = userDTO.gender();
        this.email = userDTO.email();
        this.password = userDTO.password();
    }

    public void update(UserUpdateDTO dto) {

        if (dto.name() != null) {
            this.name = dto.name();
        }

        if (dto.cpf() != null) {
            this.cpf = dto.cpf();
        }

        if (dto.email() != null) {
            this.email = dto.email();
        }

        if (dto.password() != null) {
            this.password = dto.password();
        }

        if (dto.birthday() != null) {
            this.birthday = dto.birthday();
        }

        if (dto.gender() != null) {
            this.gender = dto.gender();
        }
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
