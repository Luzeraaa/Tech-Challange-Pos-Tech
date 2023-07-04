package br.com.watchwatt.watchwatt.dao.kinship;

import br.com.watchwatt.watchwatt.domain.kinship.Kinship;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KinshipRepository extends JpaRepository<Kinship, Long> {
    @Modifying
    @Transactional
    void deleteByIdAndUserId(Long kinshipId, Long userId);

    List<Kinship> findByIdAndUserId(Long kinshipId, Long userId);
}
