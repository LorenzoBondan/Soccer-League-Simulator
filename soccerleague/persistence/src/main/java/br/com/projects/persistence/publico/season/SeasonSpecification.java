package br.com.projects.persistence.publico.season;

import br.com.projects.persistence.entities.Season;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class SeasonSpecification extends SearchSpecificationImpl<Season> {

    public SeasonSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
