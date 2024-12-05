package br.com.projects.persistence.publico.trophy;

import br.com.projects.persistence.entities.Trophy;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class TrophySpecification extends SearchSpecificationImpl<Trophy> {

    public TrophySpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
