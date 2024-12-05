package br.com.projects.persistence.publico.stadiumattachment;

import br.com.projects.persistence.entities.StadiumAttachment;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class StadiumAttachmentSpecification extends SearchSpecificationImpl<StadiumAttachment> {

    public StadiumAttachmentSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
