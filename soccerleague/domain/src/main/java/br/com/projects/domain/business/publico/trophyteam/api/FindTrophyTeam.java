package br.com.projects.domain.business.publico.trophyteam.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.trophyteam.DTrophyTeam;

public interface FindTrophyTeam {

    DTrophyTeam find (Integer id);
    Paged<DTrophyTeam> find (PageableRequest request);
}
