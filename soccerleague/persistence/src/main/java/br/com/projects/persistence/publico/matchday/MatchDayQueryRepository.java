package br.com.projects.persistence.publico.matchday;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.MatchDay;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface MatchDayQueryRepository extends PagingAndSortingRepository<MatchDay, Integer>, JpaSpecificationExecutor<MatchDay> {

    Collection<MatchDay> findByChampionship_IdAndNumber(Integer championshipId, Integer number);
}
