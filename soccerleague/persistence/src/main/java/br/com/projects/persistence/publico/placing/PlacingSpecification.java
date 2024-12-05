package br.com.projects.persistence.publico.placing;

import br.com.projects.persistence.entities.Placing;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class PlacingSpecification extends SearchSpecificationImpl<Placing> {

    public PlacingSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
