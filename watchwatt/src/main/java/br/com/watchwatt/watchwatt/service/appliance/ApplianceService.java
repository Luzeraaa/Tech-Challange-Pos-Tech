package br.com.watchwatt.watchwatt.service.appliance;

import br.com.watchwatt.watchwatt.dao.appliance.ApplianceRepository;
import br.com.watchwatt.watchwatt.dao.user.UserRepository;
import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.domain.user.User;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceUpdateDTO;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.util.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplianceService {

    private static final String APPLIANCE_NOT_FOUND = "Appliance not found";
    private final ApplianceRepository repository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ApplianceService(ApplianceRepository repository, UserRepository userRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Appliance registerAppliance(ApplianceDTO applianceDTO, final String cpf) {
        final var user = userRepository.findByCpf(cpf).orElseThrow(() -> new BadRequestException("User not found"));
        final var applianceModel = repository.findByName(applianceDTO.name()).orElseThrow(
                () -> new RuntimeException("It was not possible to recover the appliance"));

        applianceIsPresent(user, modelMapper.map(applianceModel, Appliance.class));

        return repository.save(new Appliance(applianceDTO, user));
    }

    private void applianceIsPresent(User user, Appliance appliance) {
        final var applianceIsPresent = user.getAppliances()
                .stream()
                .anyMatch(a -> a.equals(appliance));

        if (applianceIsPresent) {
            throw new RuntimeException("Appliance already registered for this user");
        }
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
}
