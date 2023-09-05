package br.com.watchwatt.watchwatt.dao.kinship;

import br.com.watchwatt.watchwatt.domain.kinship.Kinship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KinshipRepository extends JpaRepository<Kinship, Long> {
}
