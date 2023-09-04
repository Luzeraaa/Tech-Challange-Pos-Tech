package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import br.com.watchwatt.watchwatt.service.appliance.ApplianceService;
import br.com.watchwatt.watchwatt.util.Pagination;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
@RequestMapping(path = "/appliances")
public record ApplianceController(
        ApplianceService service
) implements Controller {

    private static final String APPLIANCE_ID_PATH = "/appliances";
    private static final String APPLIANCE_ADDRESS_PATH = "/address/id";
    private static final String ADDRESS_ID = "addressId";
    private static final String ID = "id";
    private static final String ALL = "all";
    private static final String ALL_PARAM = "all_param";
    private static final String TEN = "10";
    private static final String ZERO = "0";

    @PostMapping(headers = X_API_VERSION_1, path = APPLIANCE_ADDRESS_PATH, params = ADDRESS_ID)
    public ResponseEntity<Appliance> registerAppliance(
            Long addressId,
            @RequestBody
            @Valid
            ApplianceDTO applianceDTO,
            UriComponentsBuilder uriBuilder,
            Authentication auth
    ) {
        var appliance = service.registerAppliance(applianceDTO, auth, addressId);

        return ResponseEntity
                .created(uriBuilder.path(APPLIANCE_ID_PATH).buildAndExpand(appliance.getId()).toUri())
                .body(appliance);
    }

    @GetMapping(headers = X_API_VERSION_1, params = {ID})
    public ResponseEntity<Appliance> getApplianceById(Long id) {
        var Appliance = service.getApplianceById(id);

        return ResponseEntity.ok(Appliance);
    }

    @GetMapping(headers = X_API_VERSION_1, path = APPLIANCE_ADDRESS_PATH, params = ADDRESS_ID)
    public ResponseEntity<Page<Appliance>> getApplianceByAddress(
            Long addressId,
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset
    ) {
        var appliances = service.getAllApplianceByAddress(limit, offset, addressId);

        return ResponseEntity.ok(appliances);
    }

    @GetMapping(headers = X_API_VERSION_1, path = {ALL})
    public ResponseEntity<Pagination<Appliance>> getAllAppliance(
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset
    ) {
        var appliances = service.getAllAppliance(limit, offset);

        return ResponseEntity.ok(appliances);
    }

    @PutMapping(headers = X_API_VERSION_1, params = {ID})
    public ResponseEntity<Appliance> updateAppliance(@RequestBody @Valid final ApplianceUpdateDTO dto, final Long id) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(headers = X_API_VERSION_1, params = {ID})
    public ResponseEntity<Appliance> deleteAppliance(final Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(headers = X_API_VERSION_1, path = ALL_PARAM)
    public ResponseEntity<Pagination<Appliance>> getAppliances(
            @RequestParam(required = false) final List<Long> id,
            @RequestParam(required = false) final List<String> model,
            @RequestParam(required = false) final List<String> name,
            @RequestParam(required = false) final List<Integer> power,
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset
    ) {
        var appliances = service.getApplianceBy(id, model, name, power, limit, offset);

        return ResponseEntity.ok(appliances);
    }
}
