package br.com.projects.domain.business.publico.player.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.player.DPlayer;

import java.util.Collection;

public interface CrudPlayer extends SimpleCrud<DPlayer, Integer> {

    Collection<? extends DPlayer> findByName (String name);
}
