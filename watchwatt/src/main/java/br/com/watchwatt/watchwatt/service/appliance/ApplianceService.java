package br.com.watchwatt.watchwatt.service.appliance;

import br.com.watchwatt.watchwatt.dao.address.AddressRepository;
import br.com.watchwatt.watchwatt.dao.appliance.ApplianceRepository;
import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.domain.user.User;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.exception.ResourceAlreadyExistsException;
import br.com.watchwatt.watchwatt.util.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplianceService {

    private static final String APPLIANCE_NOT_FOUND = "Appliance not found";
    private static final String APPLIANCE_ALREADY_REGISTERED = "Appliance already registered for this address";
    private final ApplianceRepository repository;
    private final AddressRepository addressRepository;

    public ApplianceService(ApplianceRepository repository, AddressRepository addressRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Appliance registerAppliance(ApplianceDTO applianceDTO, Authentication auth, Long idAddress) {
        var user = (User) auth.getPrincipal();
        var address = addressRepository.findByIdAndUserId(idAddress, user.getId())
                .orElseThrow(() -> new NotFoundException(APPLIANCE_NOT_FOUND));

        repository.findByNameAndAddressId(applianceDTO.name(), idAddress).ifPresent(it -> {
            throw new ResourceAlreadyExistsException(APPLIANCE_ALREADY_REGISTERED);
        });

        return repository.save(new Appliance(applianceDTO, address));
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

    public Pagination<Appliance> getApplianceBy(
            List<Long> id,
            List<String> model,
            List<String> name,
            List<Integer> power,
            Integer limit,
            Integer offset
    ) {
        var pageRequest = PageRequest.of(offset, limit);
        var appliance = repository.findAllBy(id, model, name, pageRequest);

        return new Pagination<>(appliance);
    }

    public Pagination<Appliance> getAllApplianceByAddressId(Integer limit, Integer offset, Long idAddress) {
        var pageRequest = PageRequest.of(offset, limit);
        var address = repository.findAllByAddressId(idAddress, pageRequest);

        return new Pagination<>(address);
    }
}
