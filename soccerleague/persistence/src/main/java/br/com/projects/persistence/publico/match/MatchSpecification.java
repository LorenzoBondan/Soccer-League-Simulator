package br.com.projects.persistence.publico.match;

import br.com.projects.persistence.entities.Match;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class MatchSpecification extends SearchSpecificationImpl<Match> {

    public MatchSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
