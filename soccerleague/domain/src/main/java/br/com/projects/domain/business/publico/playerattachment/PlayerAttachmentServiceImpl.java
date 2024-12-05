package br.com.projects.domain.business.publico.playerattachment;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.playerattachment.api.PlayerAttachmentService;
import br.com.projects.domain.business.publico.playerattachment.spi.CrudPlayerAttachment;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class PlayerAttachmentServiceImpl implements PlayerAttachmentService {

    private final CrudPlayerAttachment crudPlayerAttachment;

    public PlayerAttachmentServiceImpl(CrudPlayerAttachment crudPlayerAttachment) {
        this.crudPlayerAttachment = crudPlayerAttachment;
    }

    @Override
    public DPlayerAttachment find(Integer id) {
        return crudPlayerAttachment.find(id);
    }

    @Override
    public Paged<DPlayerAttachment> find(PageableRequest request) {
        return crudPlayerAttachment.findAll(request);
    }

    @Override
    public DPlayerAttachment insert(DPlayerAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudPlayerAttachment.insert(domain);
    }

    @Override
    public DPlayerAttachment update(DPlayerAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudPlayerAttachment.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudPlayerAttachment.delete(id);
    }

    private void validateDuplicatedResource(DPlayerAttachment domain){
        if(crudPlayerAttachment.findByPlayerAndAttachment(domain.getPlayer().getId(), domain.getAttachment().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Player and Attachment.");
        }
    }
}
