package br.com.projects.domain.business.publico.countryattachment.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.countryattachment.DCountryAttachment;

public interface FindCountryAttachment {

    DCountryAttachment find (Integer id);
    Paged<DCountryAttachment> find (PageableRequest request);
}
