package br.com.watchwatt.watchwatt.dao.appliance;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

  Optional<Appliance> findByName(String name);
}
