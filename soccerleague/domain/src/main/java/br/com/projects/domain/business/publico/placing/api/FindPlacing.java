package br.com.projects.domain.business.publico.placing.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.placing.DPlacing;

public interface FindPlacing {

    DPlacing find (Integer id);
    Paged<DPlacing> find (PageableRequest request);
}
