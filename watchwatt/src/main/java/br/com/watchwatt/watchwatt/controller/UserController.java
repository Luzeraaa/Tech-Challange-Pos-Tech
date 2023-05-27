package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.user.User;
import br.com.watchwatt.watchwatt.dto.user.UserDTO;
import br.com.watchwatt.watchwatt.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import static br.com.watchwatt.watchwatt.controller.Constant.X_API_VERSION_1;

@RestController
@RequestMapping(path = "/user")
public record UserController(
        UserService service
) {

  private static final String USER_ID_PATH = "/{id}";

  @PostMapping(headers = X_API_VERSION_1)
  public ResponseEntity<User> registerUser(@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriBuilder) {
    var user = service.registerUser(userDTO);

    return ResponseEntity
            .created(uriBuilder.path(USER_ID_PATH).buildAndExpand(user.getId()).toUri())
            .body(user);
  }
}
