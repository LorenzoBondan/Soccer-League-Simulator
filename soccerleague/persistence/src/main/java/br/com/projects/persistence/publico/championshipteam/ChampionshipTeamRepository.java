package br.com.projects.persistence.publico.championshipteam;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.ChampionshipTeam;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface ChampionshipTeamRepository extends JpaRepository<ChampionshipTeam, Integer> {

}
