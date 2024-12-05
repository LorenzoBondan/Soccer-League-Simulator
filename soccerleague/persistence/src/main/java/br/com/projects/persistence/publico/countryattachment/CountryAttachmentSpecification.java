package br.com.projects.persistence.publico.countryattachment;

import br.com.projects.persistence.entities.CountryAttachment;
import br.com.projects.persistence.util.SearchCriteria;
import br.com.projects.persistence.util.SearchSpecificationImpl;

public class CountryAttachmentSpecification extends SearchSpecificationImpl<CountryAttachment> {

    public CountryAttachmentSpecification(SearchCriteria criteria) {
        super(criteria);
    }
}
