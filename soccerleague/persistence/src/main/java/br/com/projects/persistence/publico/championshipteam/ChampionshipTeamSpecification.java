package br.com.projects.persistence.publico.championshipteam;

import br.com.projects.persistence.entities.ChampionshipTeam;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class ChampionshipTeamSpecification extends SearchSpecificationImpl<ChampionshipTeam> {

    public ChampionshipTeamSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
