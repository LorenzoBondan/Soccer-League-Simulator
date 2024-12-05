package br.com.projects.domain.business.publico.stadiumattachment;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.stadiumattachment.api.StadiumAttachmentService;
import br.com.projects.domain.business.publico.stadiumattachment.spi.CrudStadiumAttachment;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class StadiumAttachmentServiceImpl implements StadiumAttachmentService {

    private final CrudStadiumAttachment crudStadiumAttachment;

    public StadiumAttachmentServiceImpl(CrudStadiumAttachment crudStadiumAttachment) {
        this.crudStadiumAttachment = crudStadiumAttachment;
    }

    @Override
    public DStadiumAttachment find(Integer id) {
        return crudStadiumAttachment.find(id);
    }

    @Override
    public Paged<DStadiumAttachment> find(PageableRequest request) {
        return crudStadiumAttachment.findAll(request);
    }

    @Override
    public DStadiumAttachment insert(DStadiumAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudStadiumAttachment.insert(domain);
    }

    @Override
    public DStadiumAttachment update(DStadiumAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudStadiumAttachment.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudStadiumAttachment.delete(id);
    }

    private void validateDuplicatedResource(DStadiumAttachment domain){
        if(crudStadiumAttachment.findByStadiumAndAttachment(domain.getStadium().getId(), domain.getAttachment().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Stadium and Attachment.");
        }
    }
}
