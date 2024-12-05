package br.com.projects.persistence.publico.team;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Team;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface TeamQueryRepository extends PagingAndSortingRepository<Team, Integer>, JpaSpecificationExecutor<Team> {

    Collection<Team> findByNameIgnoreCase(String name);
}
