package br.com.projects.persistence.publico.position;

import br.com.projects.persistence.entities.Position;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class PositionSpecification extends SearchSpecificationImpl<Position> {

    public PositionSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
