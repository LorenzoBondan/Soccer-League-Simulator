package br.com.projects.domain.business.publico.playerattachment.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.playerattachment.DPlayerAttachment;

public interface FindPlayerAttachment {

    DPlayerAttachment find (Integer id);
    Paged<DPlayerAttachment> find (PageableRequest request);
}
