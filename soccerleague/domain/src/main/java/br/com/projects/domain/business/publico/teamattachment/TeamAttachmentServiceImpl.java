package br.com.projects.domain.business.publico.teamattachment;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.teamattachment.api.TeamAttachmentService;
import br.com.projects.domain.business.publico.teamattachment.spi.CrudTeamAttachment;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class TeamAttachmentServiceImpl implements TeamAttachmentService {

    private final CrudTeamAttachment crudTeamAttachment;

    public TeamAttachmentServiceImpl(CrudTeamAttachment crudTeamAttachment) {
        this.crudTeamAttachment = crudTeamAttachment;
    }

    @Override
    public DTeamAttachment find(Integer id) {
        return crudTeamAttachment.find(id);
    }

    @Override
    public Paged<DTeamAttachment> find(PageableRequest request) {
        return crudTeamAttachment.findAll(request);
    }

    @Override
    public DTeamAttachment insert(DTeamAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTeamAttachment.insert(domain);
    }

    @Override
    public DTeamAttachment update(DTeamAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTeamAttachment.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudTeamAttachment.delete(id);
    }

    private void validateDuplicatedResource(DTeamAttachment domain){
        if(crudTeamAttachment.findByTeamAndAttachment(domain.getTeam().getId(), domain.getAttachment().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Team and Attachment.");
        }
    }
}
