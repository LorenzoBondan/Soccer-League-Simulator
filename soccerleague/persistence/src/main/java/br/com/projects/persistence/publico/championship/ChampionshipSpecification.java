package br.com.projects.persistence.publico.championship;

import br.com.projects.persistence.entities.Championship;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class ChampionshipSpecification extends SearchSpecificationImpl<Championship> {

    public ChampionshipSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
