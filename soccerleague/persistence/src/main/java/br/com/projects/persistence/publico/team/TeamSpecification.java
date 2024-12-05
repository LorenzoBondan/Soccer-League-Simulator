package br.com.projects.persistence.publico.team;

import br.com.projects.persistence.entities.Team;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class TeamSpecification extends SearchSpecificationImpl<Team> {

    public TeamSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
