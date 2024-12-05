package br.com.projects.domain.business.publico.placing.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.placing.DPlacing;

import java.util.Collection;

public interface CrudPlacing extends SimpleCrud<DPlacing, Integer> {

    Collection<? extends DPlacing> findByChampionshipAndTeam (Integer championshipId, Integer teamId);
}
