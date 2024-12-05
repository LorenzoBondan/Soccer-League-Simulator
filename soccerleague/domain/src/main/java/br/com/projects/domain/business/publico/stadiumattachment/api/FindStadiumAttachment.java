package br.com.projects.domain.business.publico.stadiumattachment.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.stadiumattachment.DStadiumAttachment;

public interface FindStadiumAttachment {

    DStadiumAttachment find (Integer id);
    Paged<DStadiumAttachment> find (PageableRequest request);
}
