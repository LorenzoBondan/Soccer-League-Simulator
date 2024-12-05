package br.com.projects.persistence.publico.country;

import br.com.projects.persistence.entities.Country;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class CountrySpecification extends SearchSpecificationImpl<Country> {

    public CountrySpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
