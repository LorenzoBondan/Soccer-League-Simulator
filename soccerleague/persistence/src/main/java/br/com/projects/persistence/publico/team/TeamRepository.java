package br.com.projects.persistence.publico.team;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface TeamRepository extends JpaRepository<Team, Integer> {

}
