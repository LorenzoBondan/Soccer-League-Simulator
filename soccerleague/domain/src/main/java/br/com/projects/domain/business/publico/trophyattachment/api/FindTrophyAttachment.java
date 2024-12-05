package br.com.projects.domain.business.publico.trophyattachment.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.trophyattachment.DTrophyAttachment;

public interface FindTrophyAttachment {

    DTrophyAttachment find (Integer id);
    Paged<DTrophyAttachment> find (PageableRequest request);
}
