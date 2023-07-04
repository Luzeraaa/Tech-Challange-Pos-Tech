package br.com.watchwatt.watchwatt.service.kinship;

import br.com.watchwatt.watchwatt.dao.kinship.KinshipRepository;
import br.com.watchwatt.watchwatt.domain.kinship.Kinship;
import br.com.watchwatt.watchwatt.dto.kinship.KinshipDTO;
import br.com.watchwatt.watchwatt.exception.NotFoundException;
import br.com.watchwatt.watchwatt.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KinshipService {

    private final UserService userService;
    private final KinshipRepository kinshipRepository;
    private static final String KINSHIP_NOT_FUND_MESSAGE = "kinship not found";

    public KinshipService(UserService userService, KinshipRepository kinshipRepository) {
        this.userService = userService;
        this.kinshipRepository = kinshipRepository;
    }

    public List<Kinship> getKinshipByCpf(String cpf) {
        return userService.findUserByCpf(cpf).getKinship();
    }

    @Transactional
    public List<Kinship> addKinshipByCpf(List<KinshipDTO> kinshipDTO, String cpf) {
        var user = userService.findUserByCpf(cpf);
        var kinship = kinshipDTO.stream()
                .map(it -> new Kinship(it.name(), it.degreeKinship(), user))
                .collect(Collectors.toSet());

        return kinshipRepository.saveAll(kinship);
    }

    @Transactional
    public Kinship updateByUserIdAndKinshipId(KinshipDTO kinshipDTO, Long userId, Long kinshipId) {
        var kinship = kinshipRepository.findByIdAndUserId(kinshipId, userId);
        validateExistsKinship(kinship);

        var newKinship = kinship.stream()
                .findFirst()
                .map(it -> new Kinship(it.getId(), kinshipDTO.name(), kinshipDTO.degreeKinship(), it.getUser()))
                .orElseThrow(() -> new NotFoundException(KINSHIP_NOT_FUND_MESSAGE));

        return kinshipRepository.save(newKinship);
    }

    @Transactional
    public void deleteKinshipByCpfAndId(Long userId, Long kinshipId) {
        var kinship = kinshipRepository.findByIdAndUserId(kinshipId, userId);
        validateExistsKinship(kinship);

        kinshipRepository.deleteAll(kinship);
    }

    private void validateExistsKinship(List<Kinship> kinship) {
        if (kinship.isEmpty()) {
            throw new NotFoundException(KINSHIP_NOT_FUND_MESSAGE);
        }
    }
}
