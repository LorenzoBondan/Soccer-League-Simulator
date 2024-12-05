package br.com.projects.persistence.publico.matchevent;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.MatchEvent;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface MatchEventRepository extends JpaRepository<MatchEvent, Integer> {

}
