package br.com.watchwatt.watchwatt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.address.AddressDTO;
import br.com.watchwatt.watchwatt.dto.address.AddressUpdateDTO;
import br.com.watchwatt.watchwatt.dto.address.viacep.ViaCepAddressDTO;
import br.com.watchwatt.watchwatt.service.address.AddressService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/address")
public record AddressController(
        AddressService service
) implements Headers {

  private static final String ADDRESS_ID_PATH = "/address";
  private static final String VIA_CEP_PATH = "/via_cep";
  private static final String ID = "id";
  private static final String ALL = "all";

  @GetMapping(headers = X_API_VERSION_1, params = {ID})
  public ResponseEntity<Optional<Address>> getAddressById(@RequestParam long id) {
    var address = service.getAddressById(id);

    return ResponseEntity.ok(address);
  }

  @GetMapping(path = ALL, headers = X_API_VERSION_1)
  public ResponseEntity<List<Address>> getAllAddress() {
    var addresses = service.getAddresses();

    return ResponseEntity.ok(addresses);
  }

  @PostMapping(headers = X_API_VERSION_1)
  public ResponseEntity<Address> registerAddress(
          @RequestBody
          @Valid
          AddressDTO addressDTO,
          UriComponentsBuilder uriBuilder
  ) {
    var address = service.registerAddress(addressDTO);

    return ResponseEntity
            .created(uriBuilder.path(ADDRESS_ID_PATH).buildAndExpand(address.getId()).toUri())
            .body(address);
  }

  @PostMapping(path = VIA_CEP_PATH, headers = X_API_VERSION_1)
  public ResponseEntity<Address> registerAddressViaCep(
          @RequestBody
          @Valid
          ViaCepAddressDTO viaCepAddressDTO,
          UriComponentsBuilder uriBuilder
  ) {
    var address = service.registerAddressViaCep(viaCepAddressDTO);

    return ResponseEntity
            .created(uriBuilder.path(VIA_CEP_PATH).buildAndExpand(address.getId()).toUri())
            .body(address);
  }

  @PutMapping(headers = X_API_VERSION_1, params = {ID})
  public ResponseEntity<String> updateAddress(@RequestBody @Valid final AddressUpdateDTO dto, final @RequestParam Long id ){
	  service.update(id, dto);
	  return ResponseEntity.ok("Endereço atualizado com sucesso");
  }

  @DeleteMapping(headers = X_API_VERSION_1, params = {ID})
  public ResponseEntity<Appliance> deleteAddress(final @RequestParam Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();

  }
}