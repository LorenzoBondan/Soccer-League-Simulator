package br.com.projects.domain.business.publico.trophy.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.trophy.DTrophy;

import java.util.Collection;

public interface CrudTrophy extends SimpleCrud<DTrophy, Integer> {

    Collection<? extends DTrophy> findByName (String name);
}
