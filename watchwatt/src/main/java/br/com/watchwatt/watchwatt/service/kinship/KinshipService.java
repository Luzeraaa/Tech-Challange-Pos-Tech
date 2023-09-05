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
    private static final String ADDRESS_NOT_FOUND = "Address not found";
    private static final String KINSHIP_ALREADY_REGISTER = "Kinship already register for this address";
    private final KinshipRepository kinshipRepository;
    private final AddressRepository addressRepository;

    public KinshipService(KinshipRepository kinshipRepository, AddressRepository addressRepository) {
        this.kinshipRepository = kinshipRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public List<Kinship> addKinshipByAddress(List<KinshipDTO> kinshipDTO, Long idAddress) {
        var address = addressRepository.findById(idAddress)
                .orElseThrow(() -> new BadRequestException(ADDRESS_NOT_FOUND));

        var kinship = kinshipDTO.stream()
                .map(it -> {
                    final var kinshipEqual = address
                            .getKinships()
                            .stream()
                            .filter(kin -> kin.getName().equalsIgnoreCase(it.name()) || kin.getDegreeKinship().equals(it.degreeKinship()))
                            .count();

                    if (kinshipEqual == 0) {
                        return new Kinship(it.name(), it.degreeKinship(), address);
                    }

                    throw new ResourceAlreadyExistsException(KINSHIP_ALREADY_REGISTER);
                })
                .collect(Collectors.toSet());

        return kinshipRepository.saveAll(kinship);
    }

    @Transactional
    public void deleteKinshipByCpfAndId(Long kinshipId) {
        var kinship = kinshipRepository.findById(kinshipId);
        var kinshipResult = kinship.orElseThrow(() -> new NotFoundException(KINSHIP_NOT_FUND_MESSAGE));

        kinshipRepository.delete(kinshipResult);
    }

    private void validateExistsKinship(List<Kinship> kinship) {
        if (kinship.isEmpty()) {
            throw new NotFoundException(KINSHIP_NOT_FUND_MESSAGE);
        }
    }
}
