package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.dto.address.AddressDTO;
import br.com.watchwatt.watchwatt.service.address.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import static br.com.watchwatt.watchwatt.controller.Constant.X_API_VERSION_1;

@RestController
@RequestMapping(path = "/address")
public record AddressController(
        AddressService service
) {

  private static final String ADDRESS_ID_PATH = "/address";

  @PostMapping(headers = X_API_VERSION_1)
  public ResponseEntity<Address> registerAddress(@RequestBody @Valid AddressDTO addressDTO, UriComponentsBuilder uriBuilder) {
    var address = service.registerAddress(addressDTO);

    return ResponseEntity
            .created(uriBuilder.path(ADDRESS_ID_PATH).buildAndExpand(address.getId()).toUri())
            .body(address);
  }
}
