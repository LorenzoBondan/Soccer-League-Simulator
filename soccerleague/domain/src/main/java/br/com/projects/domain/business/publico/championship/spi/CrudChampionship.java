package br.com.projects.domain.business.publico.championship.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.championship.DChampionship;

import java.util.Collection;

public interface CrudChampionship extends SimpleCrud<DChampionship, Integer> {

    Collection<? extends DChampionship> findByLeagueAndSeason (Integer leagueId, Integer seasonId);
}
