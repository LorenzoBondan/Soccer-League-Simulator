package br.com.projects.persistence.publico.matchday;

import br.com.projects.persistence.entities.MatchDay;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class MatchDaySpecification extends SearchSpecificationImpl<MatchDay> {

    public MatchDaySpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
