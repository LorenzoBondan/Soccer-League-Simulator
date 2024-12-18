package br.com.projects.persistence.publico.position;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
