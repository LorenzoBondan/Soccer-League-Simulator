package br.com.projects.domain.business.publico.playerstats.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.playerstats.DPlayerStats;

import java.util.Collection;

public interface CrudPlayerStats extends SimpleCrud<DPlayerStats, Integer> {

    Collection<? extends DPlayerStats> findByPlayerAndChampionship (Integer playerId, Integer championshipId);
    Collection<? extends DPlayerStats> findTopScorersByChampionship (Integer championshipId);
}
