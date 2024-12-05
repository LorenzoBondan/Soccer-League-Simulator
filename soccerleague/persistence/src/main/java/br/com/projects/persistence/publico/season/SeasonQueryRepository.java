package br.com.projects.persistence.publico.season;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Season;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface SeasonQueryRepository extends PagingAndSortingRepository<Season, Integer>, JpaSpecificationExecutor<Season> {

    Collection<Season> findBySeasonYear(Integer year);
}
