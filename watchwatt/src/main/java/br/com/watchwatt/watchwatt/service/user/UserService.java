package br.com.watchwatt.watchwatt.service.user;

import br.com.watchwatt.watchwatt.dao.user.UserRepository;
import br.com.watchwatt.watchwatt.domain.user.User;
import br.com.watchwatt.watchwatt.dto.user.UserDTO;
import br.com.watchwatt.watchwatt.exception.ResourceAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;
  private static final String USER_ALREADY_REGISTERED = "User already registered";

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public User registerUser(UserDTO useDTO) {
    var user = repository.findByCpf(useDTO.getCPF());

    user.ifPresent(it -> {
      throw new ResourceAlreadyExistsException(USER_ALREADY_REGISTERED);
    });

    return repository.save(new User(useDTO));
  }
}
