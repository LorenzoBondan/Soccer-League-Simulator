package br.com.projects.persistence.publico.league;

import br.com.projects.persistence.entities.League;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class LeagueSpecification extends SearchSpecificationImpl<League> {

    public LeagueSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
