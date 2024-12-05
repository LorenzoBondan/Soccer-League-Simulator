package br.com.projects.domain.business.publico.leagueattachment;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.leagueattachment.api.LeagueAttachmentService;
import br.com.projects.domain.business.publico.leagueattachment.spi.CrudLeagueAttachment;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class LeagueAttachmentServiceImpl implements LeagueAttachmentService {

    private final CrudLeagueAttachment crudLeagueAttachment;

    public LeagueAttachmentServiceImpl(CrudLeagueAttachment crudLeagueAttachment) {
        this.crudLeagueAttachment = crudLeagueAttachment;
    }

    @Override
    public DLeagueAttachment find(Integer id) {
        return crudLeagueAttachment.find(id);
    }

    @Override
    public Paged<DLeagueAttachment> find(PageableRequest request) {
        return crudLeagueAttachment.findAll(request);
    }

    @Override
    public DLeagueAttachment insert(DLeagueAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudLeagueAttachment.insert(domain);
    }

    @Override
    public DLeagueAttachment update(DLeagueAttachment domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudLeagueAttachment.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudLeagueAttachment.delete(id);
    }

    private void validateDuplicatedResource(DLeagueAttachment domain){
        if(crudLeagueAttachment.findByLeagueAndAttachment(domain.getLeague().getId(), domain.getAttachment().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of League and Attachment.");
        }
    }
}
