package br.com.projects.persistence.publico.matchday;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.MatchDay;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface MatchDayRepository extends JpaRepository<MatchDay, Integer> {

}
