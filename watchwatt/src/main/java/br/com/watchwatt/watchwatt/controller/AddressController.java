package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.address.AddressDTO;
import br.com.watchwatt.watchwatt.dto.address.AddressUpdateDTO;
import br.com.watchwatt.watchwatt.dto.address.viacep.ViaCepAddressDTO;
import br.com.watchwatt.watchwatt.service.address.AddressService;
import br.com.watchwatt.watchwatt.util.Pagination;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/address")
public record AddressController(
        AddressService service
) implements Controller {

    private static final String ADDRESS_ID_PATH = "/address";
    private static final String VIA_CEP_PATH = "/via_cep";
    private static final String ID = "id";
    private static final String ALL = "all";
    private static final String TEN = "10";
    private static final String ZERO = "0";
    private static final String ADDRESS_MESSAGE = "Address updated successfully";
    private static final String ZIPCODE = "zipcode";
    private static final String STREET = "street";
    private static final String NUMBER = "number";
    private static final String NEIGHBORHOOD = "neighborhood";
    private static final String CITY = "city";
    private static final String STATE = "state";

    @GetMapping(headers = X_API_VERSION_1, params = {ID})
    public ResponseEntity<Address> getAddressById(@RequestParam long id) {
        var address = service.getAddressById(id);

        return ResponseEntity.ok(address);
    }

    @GetMapping(headers = X_API_VERSION_1, path = ALL)
    public ResponseEntity<Pagination<Address>> getAllAddress(
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset
    ) {
        var addresses = service.getAddresses(limit, offset);

        return ResponseEntity.ok(addresses);
    }

    @PostMapping(headers = X_API_VERSION_1)
    public ResponseEntity<Address> registerAddress(
            @RequestBody
            @Valid
            AddressDTO addressDTO,
            Authentication auth,
            UriComponentsBuilder uriBuilder
    ) {
        var address = service.registerAddress(addressDTO, auth);

        return ResponseEntity
                .created(uriBuilder.path(ADDRESS_ID_PATH).buildAndExpand(address.getId()).toUri())
                .body(address);
    }

    @PostMapping(headers = X_API_VERSION_1, path = VIA_CEP_PATH)
    public ResponseEntity<Address> registerAddressViaCep(
            @RequestBody
            @Valid
            ViaCepAddressDTO viaCepAddressDTO,
            UriComponentsBuilder uriBuilder,
            Authentication auth
    ) {
        var address = service.registerAddressViaCep(viaCepAddressDTO, auth);

        return ResponseEntity
                .created(uriBuilder.path(VIA_CEP_PATH).buildAndExpand(address.getId()).toUri())
                .body(address);
    }

    @PutMapping(headers = X_API_VERSION_1, params = {ID})
    public ResponseEntity<String> updateAddress(
            @RequestBody
            @Valid
            AddressUpdateDTO dto,
            @RequestParam final Long id
    ) {
        service.update(id, dto);

        return ResponseEntity.ok(ADDRESS_MESSAGE);
    }

    @DeleteMapping(headers = X_API_VERSION_1, params = {ID})
    public ResponseEntity<Appliance> deleteAddress(final @RequestParam Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(headers = X_API_VERSION_1, params = {ZIPCODE})
    public ResponseEntity<List<Address>> getAddressByZipCode(@RequestParam String zipcode) {
        var address = service.getAddressByZipCode(zipcode);

        return ResponseEntity.ok(address);
    }

    @GetMapping(headers = X_API_VERSION_1, params = {STREET})
    public ResponseEntity<List<Address>> getAddressByStreet(@RequestParam String street) {
        var address = service.getAddressByStreet(street);

        return ResponseEntity.ok(address);
    }

    @GetMapping(headers = X_API_VERSION_1, params = {NUMBER})
    public ResponseEntity<List<Address>> getAddressByNumber(@RequestParam Integer number) {
        var address = service.getAddressByNumber(number);

        return ResponseEntity.ok(address);
    }

    @GetMapping(headers = X_API_VERSION_1, params = {NEIGHBORHOOD})
    public ResponseEntity<List<Address>> getAddressByNeighborhood(@RequestParam String neighborhood) {
        var address = service.getAddressByNeighborhood(neighborhood);

        return ResponseEntity.ok(address);
    }

    @GetMapping(headers = X_API_VERSION_1, params = {CITY})
    public ResponseEntity<List<Address>> getAddressByCity(@RequestParam String city) {
        var address = service.getAddressByCity(city);

        return ResponseEntity.ok(address);
    }

    @GetMapping(headers = X_API_VERSION_1, params = {STATE})
    public ResponseEntity<List<Address>> getAddressByState(@RequestParam String state) {
        var address = service.getAddressByState(state);

        return ResponseEntity.ok(address);
    }
}
