package br.com.projects.domain.business.publico.matchday.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.matchday.DMatchDay;

public interface FindMatchDay {

    DMatchDay find (Integer id);
    Paged<DMatchDay> find (PageableRequest request);
}
