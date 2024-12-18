package br.com.projects.persistence.publico.position;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Position;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

@QueryService
public interface PositionQueryRepository extends PagingAndSortingRepository<Position, Integer>, JpaSpecificationExecutor<Position> {
}