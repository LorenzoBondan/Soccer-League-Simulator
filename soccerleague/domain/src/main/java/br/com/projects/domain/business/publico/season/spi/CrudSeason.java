package br.com.projects.domain.business.publico.season.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.season.DSeason;

import java.util.Collection;

public interface CrudSeason extends SimpleCrud<DSeason, Integer> {

    Collection<? extends DSeason> findByYear (Integer year);
}
