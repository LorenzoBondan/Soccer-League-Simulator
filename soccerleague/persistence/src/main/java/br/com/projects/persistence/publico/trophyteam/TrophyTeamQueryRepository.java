package br.com.projects.persistence.publico.trophyteam;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.TrophyTeam;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface TrophyTeamQueryRepository extends PagingAndSortingRepository<TrophyTeam, Integer>, JpaSpecificationExecutor<TrophyTeam> {

    Collection<TrophyTeam> findByTrophy_IdAndTeam_Id(Integer trophyId, Integer teamId);
}
