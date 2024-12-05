package br.com.projects.persistence.publico.placing;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Placing;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface PlacingRepository extends JpaRepository<Placing, Integer> {

}
