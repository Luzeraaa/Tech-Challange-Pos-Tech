package br.com.watchwatt.watchwatt.service.user;

import br.com.watchwatt.watchwatt.dao.user.UserRepository;
import br.com.watchwatt.watchwatt.domain.user.Role;
import br.com.watchwatt.watchwatt.domain.user.User;
import br.com.watchwatt.watchwatt.dto.user.UserDTO;
import br.com.watchwatt.watchwatt.exception.FailedDependencyException;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.exception.ResourceAlreadyExistsException;
import br.com.watchwatt.watchwatt.exception.UnauthorizedException;
import br.com.watchwatt.watchwatt.util.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private static final String CPF_ALREADY_REGISTERED = "CPF already registered";
    private static final String EMAIL_ALREADY_REGISTERED = "Email already registered";
    private static final String UNAUTHORIZED_MESSAGE = "User or password invalid";
    private static final String USER_NOT_FOUND = "User not found";
    private static final String FAILED_DEPENDENCY_DATABASE = "Error retrieving data from database";

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public User registerUser(UserDTO userDTO) {
        var user = repository.findByCpfOrEmail(userDTO.cpf(), userDTO.email());

        validateExistsCpfOrEmail(user, userDTO);

        var newUser = new User(userDTO);

        return repository.save(newUser);
    }

    public User getUserByCpf(String cpf) {
        return findUserByCpf(cpf);
    }

    public Pagination<User> getAllUser(Integer limit, Integer offset) {
        var pageRequest = PageRequest.of(offset, limit);
        var userPagination = repository.findAll(pageRequest);

        return new Pagination<>(userPagination);
    }
    

//    public User updateUser(Long id, UserDTO userDTO) {
//        var user = getUserById(id);
//
//        var updatedUser = new User(user.getId(), userDTO.cpf(), userDTO.name(), userDTO.birthday(), userDTO.email(),
//                userDTO.password(), userDTO.gender(), Role.ADMIN
//                , user.getDateCreated(), ZonedDateTime.now(), user.getKinship(),
//                null);
//
//        return repository.save(updatedUser);
//    }

    public void deleteUser(Long id) {
        var user = getUserById(id);

        repository.delete(user);
    }

    private User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    public User findUserByCpf(String cpf) {
        return findByCpf(cpf).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    private Optional<User> findByCpf(String cpf) {
        try {
            return repository.findByCpf(cpf);
        } catch (Exception ex) {
            log.error(FAILED_DEPENDENCY_DATABASE, ex);
            throw new FailedDependencyException(FAILED_DEPENDENCY_DATABASE, ex);
        }
    }

    private void validateExistsCpfOrEmail(Optional<User> user, UserDTO userDTO) {
        user.ifPresent(it -> {
            if (it.getCpf().equals(userDTO.cpf())) {
                throw new ResourceAlreadyExistsException(CPF_ALREADY_REGISTERED);
            }

            if (it.getEmail().equals(userDTO.email())) {
                throw new ResourceAlreadyExistsException(EMAIL_ALREADY_REGISTERED);
            }
        });
    }
}
