package br.com.projects.persistence.publico.matchevent;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.MatchEvent;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

@QueryService
public interface MatchEventQueryRepository extends PagingAndSortingRepository<MatchEvent, Integer>, JpaSpecificationExecutor<MatchEvent> {
}
