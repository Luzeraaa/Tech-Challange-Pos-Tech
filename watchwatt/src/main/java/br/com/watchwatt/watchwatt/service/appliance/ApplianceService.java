package br.com.watchwatt.watchwatt.service.appliance;

import br.com.watchwatt.watchwatt.dao.address.AddressRepository;
import br.com.watchwatt.watchwatt.dao.appliance.ApplianceRepository;
import br.com.watchwatt.watchwatt.domain.address.Address;
import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.exception.ResourceAlreadyExistsException;
import br.com.watchwatt.watchwatt.util.Pagination;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplianceService {

    private static final String APPLIANCE_NOT_FOUND = "Appliance not found";
    private final ApplianceRepository repository;
    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;

    public ApplianceService(ApplianceRepository repository, AddressRepository addressRepository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Appliance registerAppliance(ApplianceDTO applianceDTO, final Long idAddress) {
        final var address = addressRepository.findById(idAddress).orElseThrow(() -> new NotFoundException("Address not found"));
        repository.findByNameAndAddressId(applianceDTO.name(), idAddress).ifPresent(a ->{
            throw new ResourceAlreadyExistsException("Appliance already registered for this address");
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

    public Page<Appliance> getApplianceBy(List<Long> id, List<String> model, List<String> name, List<Integer> power, Pageable paginacao) {

        return repository.findAllBy(id, model, name, paginacao);

    }

    public Page<Appliance> getAllApplianceByAddress(Pageable pageable, Long idAddress) {

        return  repository.findAllByAddressId(idAddress, pageable);

    }
}
