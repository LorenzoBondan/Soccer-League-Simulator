package br.com.projects.persistence.publico.placing;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Placing;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface PlacingQueryRepository extends PagingAndSortingRepository<Placing, Integer>, JpaSpecificationExecutor<Placing> {

    Collection<Placing> findByChampionship_IdAndTeam_Id(Integer championshipId, Integer teamId);
}
