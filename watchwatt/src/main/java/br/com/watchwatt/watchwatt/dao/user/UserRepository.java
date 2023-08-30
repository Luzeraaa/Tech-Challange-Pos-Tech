package br.com.watchwatt.watchwatt.dao.user;

import br.com.watchwatt.watchwatt.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);

    Optional<User> findByCpfOrEmail(String cpf, String email);

    UserDetails findByEmail(String email);
}
