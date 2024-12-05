package br.com.projects.persistence.publico.league;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface LeagueRepository extends JpaRepository<League, Integer> {

}
