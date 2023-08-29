package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ListApplianceDTO;
import br.com.watchwatt.watchwatt.service.appliance.ApplianceService;
import br.com.watchwatt.watchwatt.util.Pagination;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

    private static final String NAME = "name";

    private static final String MODEL = "model";

    private static final String POWER = "power";


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


    @GetMapping(headers = X_API_VERSION_1, path = "/todos")
    public ResponseEntity<Page<ListApplianceDTO>> getAppliances(@RequestParam(required = false) final List<Long> id,
                                                                @RequestParam(required = false) final List<String> model,
                                                                @RequestParam(required = false) final List<String> name,
                                                                @RequestParam(required = false) final List<Integer> power,
                                                                @PageableDefault(size = 10) Pageable paginacao) {

        Page page = service.getApplianceBy(id, model, name, power, paginacao).map(ListApplianceDTO::new);
        return ResponseEntity.ok(page);

    }

}
