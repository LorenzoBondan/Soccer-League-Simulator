package br.com.projects.domain.business.publico.stadium.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.stadium.DStadium;

import java.util.Collection;

public interface CrudStadium extends SimpleCrud<DStadium, Integer> {

    Collection<? extends DStadium> findByName (String name);
}
