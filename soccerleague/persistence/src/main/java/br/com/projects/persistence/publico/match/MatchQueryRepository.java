package br.com.projects.persistence.publico.match;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Country;
import br.com.projects.persistence.entities.Match;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface MatchQueryRepository extends PagingAndSortingRepository<Match, Integer>, JpaSpecificationExecutor<Match> {

    Collection<Match> findByHomeTeam_IdAndAwayTeam_IdAndMatchDay_Id(Integer homeTeamId, Integer awayTeamId, Integer matchDayId);
}
