package br.com.projects.domain.business.publico.countryattachment;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.countryattachment.api.CountryAttachmentService;
import br.com.projects.domain.business.publico.countryattachment.spi.CrudCountryAttachment;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class CountryAttachmentServiceImpl implements CountryAttachmentService {

    private final CrudCountryAttachment crudCountryAttachment;

    public CountryAttachmentServiceImpl(CrudCountryAttachment crudCountryAttachment) {
        this.crudCountryAttachment = crudCountryAttachment;
    }

    @Override
    public DCountryAttachment find(Integer id) {
        return crudCountryAttachment.find(id);
    }

    @Override
    public Paged<DCountryAttachment> find(PageableRequest request) {
        return crudCountryAttachment.findAll(request);
    }

    @Override
    public DCountryAttachment insert(DCountryAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudCountryAttachment.insert(domain);
    }

    @Override
    public DCountryAttachment update(DCountryAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudCountryAttachment.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudCountryAttachment.delete(id);
    }

    private void validateDuplicatedResource(DCountryAttachment domain){
        if(crudCountryAttachment.findByCountryAndAttachment(domain.getCountry().getId(), domain.getAttachment().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Country and Attachment.");
        }
    }
}
