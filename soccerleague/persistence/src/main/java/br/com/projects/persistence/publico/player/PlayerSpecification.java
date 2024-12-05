package br.com.projects.persistence.publico.player;

import br.com.projects.persistence.entities.Player;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class PlayerSpecification extends SearchSpecificationImpl<Player> {

    public PlayerSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
