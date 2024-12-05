package br.com.projects.domain.business.publico.attachment.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.attachment.DAttachment;

public interface FindAttachment {

    DAttachment find (Integer id);
    Paged<DAttachment> find (PageableRequest request);
}
