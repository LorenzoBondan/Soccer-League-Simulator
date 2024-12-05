package br.com.projects.persistence.publico.championship;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface ChampionshipRepository extends JpaRepository<Championship, Integer> {

}
