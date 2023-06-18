package br.com.watchwatt.watchwatt.service.user;

import br.com.watchwatt.watchwatt.dao.user.UserRepository;
import br.com.watchwatt.watchwatt.domain.user.User;
import br.com.watchwatt.watchwatt.dto.user.UserDTO;
import br.com.watchwatt.watchwatt.exception.FailedDependencyException;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.exception.ResourceAlreadyExistsException;
import br.com.watchwatt.watchwatt.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

	private final UserRepository repository;
	private final PasswordEncoder encoder;
	private static final String USER_ALREADY_REGISTERED = "User already registered";
	private static final String UNAUTHORIZED_MESSAGE = "User or password invalid";
	private static final String USER_NOT_FOUND = "User not found";
	private static final String FAILED_DEPENDENCY_DATABASE = "Error retrieving data from database";

	public UserService(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	public User registerUser(UserDTO userDTO) {
		var user = findByCpf(userDTO.cpf());

		user.ifPresent(it -> {
			throw new ResourceAlreadyExistsException(USER_ALREADY_REGISTERED);
		});

		return repository.save(new User(userDTO));
	}

	public User getUserByCpf(String cpf) {
		return findByCpf(cpf).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
	}

	public List<User> getAllUser() {
		return repository.findAll();
	}

	public void validateUserAndPassword(String cpf, String password) {
		var userResult = findByCpf(cpf);
		var user = userResult.orElseThrow(() -> new UnauthorizedException(UNAUTHORIZED_MESSAGE));

		if (!encoder.matches(password, user.getPassword())) {
			throw new UnauthorizedException(UNAUTHORIZED_MESSAGE);
		}
	}

	public User updateUser(Long id, UserDTO userDTO) {
		var user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		var updatedUser = new User(user.getId(), userDTO.cpf(), userDTO.name(), userDTO.birthday(), userDTO.email(),
				userDTO.password(), userDTO.gender(), user.getParentesco(), user.getDateCreated(), ZonedDateTime.now());

		return repository.save(updatedUser);
	}

	public void deleteUser(Long id) {
		var user = repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

		repository.delete(user);
	}

	private Optional<User> findByCpf(String cpf) {
		try {
			return repository.findByCpf(cpf);
		} catch (Exception ex) {
			log.error(FAILED_DEPENDENCY_DATABASE, ex);
			throw new FailedDependencyException(FAILED_DEPENDENCY_DATABASE, ex);
		}
	}
}
