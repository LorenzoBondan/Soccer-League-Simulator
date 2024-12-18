package br.com.projects.persistence.publico.playerstats;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.PlayerStats;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface PlayerStatsQueryRepository extends PagingAndSortingRepository<PlayerStats, Integer>, JpaSpecificationExecutor<PlayerStats> {

    Collection<PlayerStats> findByPlayer_IdAndChampionship_Id(Integer playerId, Integer championshipId);
    Collection<PlayerStats> findByChampionship_IdOrderByGoalsDesc(Integer championshipId);

}
