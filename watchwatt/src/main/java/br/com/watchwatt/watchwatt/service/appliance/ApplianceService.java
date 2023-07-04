package br.com.watchwatt.watchwatt.service.appliance;

import br.com.watchwatt.watchwatt.dao.appliance.ApplianceRepository;
import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.util.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ApplianceService {

    private final ApplianceRepository repository;
    private static final String APPLIANCE_NOT_FOUND = "Appliance not found";

    public ApplianceService(ApplianceRepository repository) {
        this.repository = repository;
    }

    public Appliance registerAppliance(ApplianceDTO applianceDTO) {
        var appliance = repository.findByName(applianceDTO.name());

        appliance.ifPresent(it -> {
            throw new BadRequestException("appliance already registered");
        });

        return repository.save(new Appliance(applianceDTO));
    }

    public Appliance getApplianceById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
    }

    public Pagination<Appliance> getAllAppliance(Integer limit, Integer offset) {
        var pageRequest = PageRequest.of(offset, limit);
        var appliancePagination = repository.findAll(pageRequest);

        return new Pagination<>(appliancePagination);
    }

    public void delete(Long id) {
        var appliance = repository.findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        repository.delete(appliance);
    }

    public Appliance update(Long id, ApplianceUpdateDTO dto) {
        var appliance = repository.findById(id).orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));
        appliance.update(dto);

        return repository.save(appliance);
    }
}
