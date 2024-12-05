package br.com.projects.domain.business.publico.country.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.country.DCountry;

public interface FindCountry {

    DCountry find (Integer id);
    Paged<DCountry> find (PageableRequest request);
}
