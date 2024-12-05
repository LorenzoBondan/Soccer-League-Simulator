package br.com.projects.persistence.publico.country;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Country;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface CountryQueryRepository extends PagingAndSortingRepository<Country, Integer>, JpaSpecificationExecutor<Country> {

    Collection<Country> findByNameIgnoreCase(String name);
}
