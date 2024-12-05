package br.com.projects.persistence.publico.league;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.League;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface LeagueQueryRepository extends PagingAndSortingRepository<League, Integer>, JpaSpecificationExecutor<League> {

    Collection<League> findByNameIgnoreCase(String name);
}
