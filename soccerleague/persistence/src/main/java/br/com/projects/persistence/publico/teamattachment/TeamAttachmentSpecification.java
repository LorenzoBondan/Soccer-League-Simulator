package br.com.projects.persistence.publico.teamattachment;

import br.com.projects.persistence.entities.TeamAttachment;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class TeamAttachmentSpecification extends SearchSpecificationImpl<TeamAttachment> {

    public TeamAttachmentSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
