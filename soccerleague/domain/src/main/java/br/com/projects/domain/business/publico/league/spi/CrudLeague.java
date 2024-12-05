package br.com.projects.domain.business.publico.league.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.league.DLeague;

import java.util.Collection;

public interface CrudLeague extends SimpleCrud<DLeague, Integer> {

    Collection<? extends DLeague> findByName (String name);
}
