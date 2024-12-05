package br.com.projects.domain.business.publico.trophyteam.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.trophyteam.DTrophyTeam;

import java.util.Collection;

public interface CrudTrophyTeam extends SimpleCrud<DTrophyTeam, Integer> {

    Collection<? extends DTrophyTeam> findByTrophyAndTeam (Integer trophyId, Integer teamId);
}
