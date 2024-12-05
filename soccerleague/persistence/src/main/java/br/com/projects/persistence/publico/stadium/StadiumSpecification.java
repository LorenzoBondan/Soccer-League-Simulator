package br.com.projects.persistence.publico.stadium;

import br.com.projects.persistence.entities.Stadium;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class StadiumSpecification extends SearchSpecificationImpl<Stadium> {

    public StadiumSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
