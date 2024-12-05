package br.com.projects.domain.business.publico.championshipteam.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.championshipteam.DChampionshipTeam;

import java.util.Collection;

public interface CrudChampionshipTeam extends SimpleCrud<DChampionshipTeam, Integer> {

    Collection<? extends DChampionshipTeam> findByChampionshipAndTeam (Integer championshipId, Integer teamId);
}
