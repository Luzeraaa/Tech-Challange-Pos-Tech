package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import br.com.watchwatt.watchwatt.service.appliance.ApplianceService;
import br.com.watchwatt.watchwatt.util.Pagination;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = "/appliances")
public record ApplianceController(
        ApplianceService service
) implements Controller {

    private static final String APPLIANCE_ID_PATH = "/appliances";
    private static final String ID = "id";
    private static final String ALL = "all";
    private static final String TEN = "10";
    private static final String ZERO = "0";

    private static final String CPF = "cpf";

    @PostMapping(headers = X_API_VERSION_1, path = "/{cpf}")
    public ResponseEntity<Appliance> registerAppliance(@PathVariable String cpf,
            @RequestBody @Valid ApplianceDTO applianceDTO, UriComponentsBuilder uriBuilder) {
        var appliance = service.registerAppliance(applianceDTO, cpf);

        return ResponseEntity
                .created(uriBuilder.path(APPLIANCE_ID_PATH).buildAndExpand(appliance.getId()).toUri())
                .body(appliance);
    }

    @GetMapping(headers = X_API_VERSION_1, params = {ID})
    public ResponseEntity<Appliance> getApplianceById(Long id) {
        var Appliance = service.getApplianceById(id);

        return ResponseEntity.ok(Appliance);
    }

    @GetMapping(headers = X_API_VERSION_1, path = {ALL})
    public ResponseEntity<Pagination<Appliance>> getAllAppliance(
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset
    ) {
        var Appliances = service.getAllAppliance(limit, offset);

        return ResponseEntity.ok(Appliances);
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
}
