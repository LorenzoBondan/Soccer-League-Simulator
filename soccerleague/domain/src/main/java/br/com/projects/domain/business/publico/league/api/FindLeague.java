package br.com.projects.domain.business.publico.league.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.league.DLeague;

public interface FindLeague {

    DLeague find (Integer id);
    Paged<DLeague> find (PageableRequest request);
}
