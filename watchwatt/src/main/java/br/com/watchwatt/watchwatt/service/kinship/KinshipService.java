package br.com.watchwatt.watchwatt.service.kinship;

import br.com.watchwatt.watchwatt.dao.address.AddressRepository;
import br.com.watchwatt.watchwatt.dao.kinship.KinshipRepository;
import br.com.watchwatt.watchwatt.domain.kinship.Kinship;
import br.com.watchwatt.watchwatt.dto.kinship.KinshipDTO;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.exception.ResourceAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KinshipService {

    private static final String KINSHIP_NOT_FUND_MESSAGE = "kinship not found";
    private final KinshipRepository kinshipRepository;
    private final AddressRepository addressRepository;

    public KinshipService(KinshipRepository kinshipRepository, AddressRepository addressRepository) {
        this.kinshipRepository = kinshipRepository;
        this.addressRepository = addressRepository;
    }

//    public List<Kinship> getKinshipByCpf(String cpf) {
//        return userService.findUserByCpf(cpf).getKinship();
//    }

    @Transactional
    public List<Kinship> addKinshipByAddress(List<KinshipDTO> kinshipDTO, Long idAddress) {
        var address = addressRepository.findById(idAddress).orElseThrow(
                () -> new BadRequestException("Address not found")
        );

        var kinship = kinshipDTO.stream()
                .map(it -> {
                    final var kinshipEqual = address.getKinships().stream()
                            .filter(k -> k.getName().equalsIgnoreCase(it.name()) || k.getDegreeKinship().equals(it.degreeKinship()))
                            .count();
                    if (kinshipEqual == 0) {
                        return new Kinship(it.name(), it.degreeKinship(), address);
                    }
                    throw new ResourceAlreadyExistsException("Kinship alred register for this address");
                })
                .collect(Collectors.toSet());

        return kinshipRepository.saveAll(kinship);
    }

    @Transactional
    public Kinship updateByUserIdAndKinshipId(KinshipDTO kinshipDTO, Long userId, Long kinshipId) {
        var kinship = kinshipRepository.findByIdAndAddressUserId(kinshipId, userId);
        validateExistsKinship(kinship);

        var newKinship = kinship.stream()
                .findFirst()
                .map(it -> new Kinship(it.getId(), kinshipDTO.name(), kinshipDTO.degreeKinship(), it.getAddress()))
                .orElseThrow(() -> new NotFoundException(KINSHIP_NOT_FUND_MESSAGE));

        return kinshipRepository.save(newKinship);
    }

    @Transactional
    public void deleteKinshipByCpfAndId(Long userId, Long kinshipId) {
        var kinship = kinshipRepository.findByIdAndAddressUserId(kinshipId, userId);
        validateExistsKinship(kinship);

        kinshipRepository.deleteAll(kinship);
    }

    private void validateExistsKinship(List<Kinship> kinship) {
        if (kinship.isEmpty()) {
            throw new NotFoundException(KINSHIP_NOT_FUND_MESSAGE);
        }
    }
}
