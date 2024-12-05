package br.com.projects.domain.business.publico.championship.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.championship.DChampionship;

public interface FindChampionship {

    DChampionship find (Integer id);
    Paged<DChampionship> find (PageableRequest request);
}
