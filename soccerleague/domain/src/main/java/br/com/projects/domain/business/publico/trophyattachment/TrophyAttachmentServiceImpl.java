package br.com.projects.domain.business.publico.trophyattachment;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.trophyattachment.api.TrophyAttachmentService;
import br.com.projects.domain.business.publico.trophyattachment.spi.CrudTrophyAttachment;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class TrophyAttachmentServiceImpl implements TrophyAttachmentService {

    private final CrudTrophyAttachment crudTrophyAttachment;

    public TrophyAttachmentServiceImpl(CrudTrophyAttachment crudTrophyAttachment) {
        this.crudTrophyAttachment = crudTrophyAttachment;
    }

    @Override
    public DTrophyAttachment find(Integer id) {
        return crudTrophyAttachment.find(id);
    }

    @Override
    public Paged<DTrophyAttachment> find(PageableRequest request) {
        return crudTrophyAttachment.findAll(request);
    }

    @Override
    public DTrophyAttachment insert(DTrophyAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTrophyAttachment.insert(domain);
    }

    @Override
    public DTrophyAttachment update(DTrophyAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTrophyAttachment.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudTrophyAttachment.delete(id);
    }

    private void validateDuplicatedResource(DTrophyAttachment domain){
        if(crudTrophyAttachment.findByTrophyAndAttachment(domain.getTrophy().getId(), domain.getAttachment().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Trophy and Attachment.");
        }
    }
}
