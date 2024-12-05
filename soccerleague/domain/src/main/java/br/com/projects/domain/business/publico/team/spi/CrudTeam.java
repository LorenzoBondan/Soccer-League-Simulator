package br.com.projects.domain.business.publico.team.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.team.DTeam;

import java.util.Collection;

public interface CrudTeam extends SimpleCrud<DTeam, Integer> {

    Collection<? extends DTeam> findByName (String name);
}
