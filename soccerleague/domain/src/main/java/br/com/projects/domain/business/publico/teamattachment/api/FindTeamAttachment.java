package br.com.projects.domain.business.publico.teamattachment.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.teamattachment.DTeamAttachment;

public interface FindTeamAttachment {

    DTeamAttachment find (Integer id);
    Paged<DTeamAttachment> find (PageableRequest request);
}
