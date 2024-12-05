package br.com.projects.domain.business.publico.team.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.team.DTeam;

public interface FindTeam {

    DTeam find (Integer id);
    Paged<DTeam> find (PageableRequest request);
}
