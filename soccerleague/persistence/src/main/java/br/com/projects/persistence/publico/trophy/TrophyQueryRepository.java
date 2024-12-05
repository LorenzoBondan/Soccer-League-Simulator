package br.com.projects.persistence.publico.trophy;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Trophy;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface TrophyQueryRepository extends PagingAndSortingRepository<Trophy, Integer>, JpaSpecificationExecutor<Trophy> {

    Collection<Trophy> findByNameIgnoreCase(String name);
}
