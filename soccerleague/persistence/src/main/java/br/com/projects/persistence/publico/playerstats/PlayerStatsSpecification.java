package br.com.projects.persistence.publico.playerstats;

import br.com.projects.persistence.entities.PlayerStats;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class PlayerStatsSpecification extends SearchSpecificationImpl<PlayerStats> {

    public PlayerStatsSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
