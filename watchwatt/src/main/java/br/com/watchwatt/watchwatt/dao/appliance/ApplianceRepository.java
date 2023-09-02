package br.com.watchwatt.watchwatt.dao.appliance;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

    Optional<Appliance> findByName(String name);

    Optional<Appliance> findByNameAndAddressId(String name, Long idAddress);

    @Query(value = """
            SELECT a FROM Appliance a
            WHERE (:id is null or a.id IN(:#{#id}))
            AND (:model is null or a.model IN(:#{#model}))
            AND (:name is null or a.name IN(:#{#name}))
            """)
    Page<Appliance> findAllBy(List<Long> id,
                              List<String> model,
                              List<String> name,
                              Pageable paginacao);


    Page<Appliance> findAllByAddressId(Long idAddress, Pageable pageRequest);
}
