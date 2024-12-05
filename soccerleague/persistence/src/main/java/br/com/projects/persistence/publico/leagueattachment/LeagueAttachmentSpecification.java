package br.com.projects.persistence.publico.leagueattachment;

import br.com.projects.persistence.entities.LeagueAttachment;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class LeagueAttachmentSpecification extends SearchSpecificationImpl<LeagueAttachment> {

    public LeagueAttachmentSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
