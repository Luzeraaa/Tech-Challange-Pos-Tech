package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.service.appliance.ApplianceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/appliances")
public record ApplianceController(
        ApplianceService service
) implements Headers {

  private static final String APPLIANCE_ID_PATH = "/appliances";
  private static final String ID = "id";
  private static final String ALL = "all";

  @PostMapping(headers = X_API_VERSION_1)
  public ResponseEntity<Appliance> registerAppliance(@RequestBody @Valid ApplianceDTO applianceDTO, UriComponentsBuilder uriBuilder) {
    var appliance = service.registerAppliance(applianceDTO);

    return ResponseEntity
            .created(uriBuilder.path(APPLIANCE_ID_PATH).buildAndExpand(appliance.getId()).toUri())
            .body(appliance);
  }

  @GetMapping(headers = X_API_VERSION_1, params = {ID})
  public ResponseEntity<Appliance> getApplianceById(Long id) {
    var Appliance = service.getApplianceById(id);

    return ResponseEntity.ok(Appliance);
  }

  @GetMapping(headers = X_API_VERSION_1, path = ALL)
  public ResponseEntity<List<Appliance>> getAllAppliance() {
    var Appliances = service.getAllAppliance();

    return ResponseEntity.ok(Appliances);
  }
}