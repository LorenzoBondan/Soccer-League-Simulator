package br.com.projects.domain.business.publico.playerstats.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.playerstats.DPlayerStats;

import java.util.Collection;

public interface FindPlayerStats {

    DPlayerStats find (Integer id);
    Paged<DPlayerStats> find (PageableRequest request);
    Collection<? extends DPlayerStats> findByPlayerAndChampionship (Integer playerId, Integer championshipId);
    Collection<? extends DPlayerStats> findTopScorersByChampionship (Integer championshipId);
}
