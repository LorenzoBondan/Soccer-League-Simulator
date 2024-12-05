package br.com.projects.persistence.publico.championshipteam;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.ChampionshipTeam;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface ChampionshipTeamQueryRepository extends PagingAndSortingRepository<ChampionshipTeam, Integer>, JpaSpecificationExecutor<ChampionshipTeam> {

    Collection<ChampionshipTeam> findByChampionship_IdAndTeam_Id(Integer championshipId, Integer teamId);
}
