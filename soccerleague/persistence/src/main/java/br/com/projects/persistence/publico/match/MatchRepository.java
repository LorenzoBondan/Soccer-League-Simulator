package br.com.projects.persistence.publico.match;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface MatchRepository extends JpaRepository<Match, Integer> {

}
