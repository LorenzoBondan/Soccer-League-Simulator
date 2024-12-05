package br.com.projects.persistence.publico.matchevent;

import br.com.projects.persistence.entities.MatchEvent;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class MatchEventSpecification extends SearchSpecificationImpl<MatchEvent> {

    public MatchEventSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
