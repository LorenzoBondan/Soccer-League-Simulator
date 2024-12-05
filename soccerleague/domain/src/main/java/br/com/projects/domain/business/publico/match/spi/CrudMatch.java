package br.com.projects.domain.business.publico.match.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.match.DMatch;

import java.util.Collection;

public interface CrudMatch extends SimpleCrud<DMatch, Integer> {

    Collection<? extends DMatch> findByHomeTeamAndAwayTeamAndMatchDay (Integer homeTeamId, Integer awayTeamId, Integer matchDayId);
}
