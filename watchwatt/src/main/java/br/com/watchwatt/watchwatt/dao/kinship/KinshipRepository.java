package br.com.watchwatt.watchwatt.dao.kinship;

import br.com.watchwatt.watchwatt.domain.kinship.Kinship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KinshipRepository extends JpaRepository<Kinship, Long> {
    List<Kinship> findByIdAndAddressUserId(Long kinshipId, Long userId);
}
