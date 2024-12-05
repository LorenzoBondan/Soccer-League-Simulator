package br.com.projects.domain.business.publico.leagueattachment.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.leagueattachment.DLeagueAttachment;

public interface FindLeagueAttachment {

    DLeagueAttachment find (Integer id);
    Paged<DLeagueAttachment> find (PageableRequest request);
}
