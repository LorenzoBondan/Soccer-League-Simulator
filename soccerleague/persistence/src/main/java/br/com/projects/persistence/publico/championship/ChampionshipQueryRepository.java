package br.com.projects.persistence.publico.championship;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Championship;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface ChampionshipQueryRepository extends PagingAndSortingRepository<Championship, Integer>, JpaSpecificationExecutor<Championship> {

    Collection<Championship> findByLeague_IdAndSeason_Id(Integer leagueId, Integer seasonId);
}
