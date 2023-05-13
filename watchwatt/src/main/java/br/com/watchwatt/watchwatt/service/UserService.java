package br.com.watchwatt.watchwatt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.watchwatt.watchwatt.domain.user.Userr;
import br.com.watchwatt.watchwatt.domain.user.UserRepository;
import br.com.watchwatt.watchwatt.domain.user.dto.DataRegister;
import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public Long cadastrar(@Valid DataRegister data) {
		
		Userr user = repository.findByCpf(data.cpf());
		
		if(user != null) {

			throw new RuntimeException("Usuário já cadastrado ");
		}
		
		user = new Userr(data);

		repository.save(user);
		
		return user.getId();
	}

}
