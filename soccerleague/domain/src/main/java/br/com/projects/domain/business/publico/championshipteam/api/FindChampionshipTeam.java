package br.com.projects.domain.business.publico.championshipteam.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.championshipteam.DChampionshipTeam;

public interface FindChampionshipTeam {

    DChampionshipTeam find (Integer id);
    Paged<DChampionshipTeam> find (PageableRequest request);
}
