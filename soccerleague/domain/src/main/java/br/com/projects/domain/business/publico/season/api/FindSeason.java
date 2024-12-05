package br.com.projects.domain.business.publico.season.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.season.DSeason;

public interface FindSeason {

    DSeason find (Integer id);
    Paged<DSeason> find (PageableRequest request);
}
