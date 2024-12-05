package br.com.projects.persistence.publico.season;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface SeasonRepository extends JpaRepository<Season, Integer> {

}
