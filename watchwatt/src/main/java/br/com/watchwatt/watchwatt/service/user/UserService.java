package br.com.watchwatt.watchwatt.service.user;

import br.com.watchwatt.watchwatt.dao.user.UserRepository;
import br.com.watchwatt.watchwatt.domain.user.User;
import br.com.watchwatt.watchwatt.dto.user.UserDTO;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.exception.ResourceAlreadyExistsException;
import br.com.watchwatt.watchwatt.exception.UnauthorizedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder encoder;
  private static final String USER_ALREADY_REGISTERED = "User already registered";
  private static final String UNAUTHORIZED_MESSAGE = "User or password invalid";
  private static final String USER_NOT_FOUND = "User not found";

  public UserService(UserRepository repository, PasswordEncoder encoder) {
    this.repository = repository;
    this.encoder = encoder;
  }

  public User registerUser(UserDTO userDTO) {
    var userEncoder = userDTO.withPassword(encoder.encode(userDTO.password()));
    var user = repository.findByCpf(userEncoder.cpf());

    user.ifPresent(it -> {
      throw new ResourceAlreadyExistsException(USER_ALREADY_REGISTERED);
    });

    return repository.save(new User(userEncoder));
  }

  public User getUserById(String cpf) {
    return repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
  }

  public List<User> getAllUser() {
    return repository.findAll();
  }

  public void validateUserAndPassword(String cpf, String password) {
    var userResult = repository.findByCpf(cpf);
    var user = userResult.orElseThrow(() -> new UnauthorizedException(UNAUTHORIZED_MESSAGE));

    if (!encoder.matches(password, user.getPassword())) {
      throw new UnauthorizedException(UNAUTHORIZED_MESSAGE);
    }
  }
}
