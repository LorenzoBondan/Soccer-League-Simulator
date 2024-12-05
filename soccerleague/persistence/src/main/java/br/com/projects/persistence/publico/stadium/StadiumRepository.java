package br.com.projects.persistence.publico.stadium;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {

}
