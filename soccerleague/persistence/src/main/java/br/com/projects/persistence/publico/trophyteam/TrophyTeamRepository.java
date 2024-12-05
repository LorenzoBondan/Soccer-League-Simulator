package br.com.projects.persistence.publico.trophyteam;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.TrophyTeam;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface TrophyTeamRepository extends JpaRepository<TrophyTeam, Integer> {

}
