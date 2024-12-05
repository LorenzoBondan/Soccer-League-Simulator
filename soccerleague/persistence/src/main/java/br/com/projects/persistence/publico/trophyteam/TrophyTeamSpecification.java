package br.com.projects.persistence.publico.trophyteam;

import br.com.projects.persistence.entities.TrophyTeam;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class TrophyTeamSpecification extends SearchSpecificationImpl<TrophyTeam> {

    public TrophyTeamSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
