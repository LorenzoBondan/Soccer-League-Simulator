package br.com.projects.domain.business.publico.matchday.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.matchday.DMatchDay;

import java.util.Collection;

public interface CrudMatchDay extends SimpleCrud<DMatchDay, Integer> {

    Collection<? extends DMatchDay> findByChampionshipAndNumber (Integer championshipId, Integer number);
}
