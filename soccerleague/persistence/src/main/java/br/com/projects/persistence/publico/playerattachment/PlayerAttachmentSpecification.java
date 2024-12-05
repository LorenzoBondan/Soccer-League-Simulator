package br.com.projects.persistence.publico.playerattachment;

import br.com.projects.persistence.entities.PlayerAttachment;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class PlayerAttachmentSpecification extends SearchSpecificationImpl<PlayerAttachment> {

    public PlayerAttachmentSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
