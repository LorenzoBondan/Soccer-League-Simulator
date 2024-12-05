package br.com.projects.persistence.publico.stadium;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Stadium;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface StadiumQueryRepository extends PagingAndSortingRepository<Stadium, Integer>, JpaSpecificationExecutor<Stadium> {

    Collection<Stadium> findByNameIgnoreCase(String name);
}
