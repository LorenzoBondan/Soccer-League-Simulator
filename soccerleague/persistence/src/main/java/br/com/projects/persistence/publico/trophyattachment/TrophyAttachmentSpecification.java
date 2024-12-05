package br.com.projects.persistence.publico.trophyattachment;

import br.com.projects.persistence.entities.TrophyAttachment;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class TrophyAttachmentSpecification extends SearchSpecificationImpl<TrophyAttachment> {

    public TrophyAttachmentSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
