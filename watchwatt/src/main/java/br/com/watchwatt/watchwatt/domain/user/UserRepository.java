package br.com.watchwatt.watchwatt.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userr, Long> {

	Userr findByCpf(String cpf);

}
