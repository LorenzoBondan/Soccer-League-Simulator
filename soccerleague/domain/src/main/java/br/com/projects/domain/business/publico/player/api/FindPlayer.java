package br.com.projects.domain.business.publico.player.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.player.DPlayer;

public interface FindPlayer {

    DPlayer find (Integer id);
    Paged<DPlayer> find (PageableRequest request);
}
