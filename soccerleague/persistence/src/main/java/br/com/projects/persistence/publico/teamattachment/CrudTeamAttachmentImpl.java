package br.com.projects.persistence.publico.teamattachment;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.PagedBuilder;
import br.com.projects.domain.business.publico.teamattachment.DTeamAttachment;
import br.com.projects.domain.business.publico.teamattachment.spi.CrudTeamAttachment;
import br.com.projects.domain.exceptions.DatabaseException;
import br.com.projects.domain.exceptions.ResourceNotFoundException;
import br.com.projects.persistence.entities.TeamAttachment;
import br.com.projects.persistence.util.PageRequestUtils;
import br.com.projects.persistence.util.SpecificationHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Component
public class CrudTeamAttachmentImpl implements CrudTeamAttachment {

    private final TeamAttachmentRepository repository;
    private final TeamAttachmentQueryRepository queryRepository;
    private final TeamAttachmentDomainToEntityAdapter adapter;
    private final PageRequestUtils pageRequestUtils;

    public CrudTeamAttachmentImpl(TeamAttachmentRepository repository, TeamAttachmentQueryRepository queryRepository, TeamAttachmentDomainToEntityAdapter adapter, PageRequestUtils pageRequestUtils) {
        this.repository = repository;
        this.queryRepository = queryRepository;
        this.adapter = adapter;
        this.pageRequestUtils = pageRequestUtils;
    }

    @Override
    @Transactional(readOnly = true)
    public Paged<DTeamAttachment> findAll(PageableRequest request) {
        SpecificationHelper<TeamAttachment> helper = new SpecificationHelper<>();
        Specification<TeamAttachment> specification = helper.buildSpecification(request.getColunas(), request.getOperacoes(), request.getValores());

        return Optional.of(queryRepository.findAll(specification, pageRequestUtils.toPage(request)))
                .map(r -> new PagedBuilder<DTeamAttachment>()
                        .withContent(r.getContent().stream().map(adapter::toDomain).toList())
                        .withSortedBy(String.join(";", request.getSort()))
                        .withFirst(r.isFirst())
                        .withLast(r.isLast())
                        .withPage(r.getNumber())
                        .withSize(r.getSize())
                        .withTotalPages(r.getTotalPages())
                        .withNumberOfElements(Math.toIntExact(r.getTotalElements()))
                        .build())
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<? extends DTeamAttachment> findByTeamAndAttachment(Integer TeamId, Integer attachmentId) {
        return queryRepository.findByTeam_IdAndAttachment_Id(TeamId, attachmentId).stream().map(adapter::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DTeamAttachment find(Integer id) {
        return adapter.toDomain(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Código não encontrado: " + id)));
    }

    @Override
    @Transactional
    public DTeamAttachment insert(DTeamAttachment obj) {
        return adapter.toDomain(repository.save(adapter.toEntity(obj)));
    }

    @Override
    @Transactional
    public DTeamAttachment update(DTeamAttachment obj) {
        if(!repository.existsById(obj.getId())){
            throw new ResourceNotFoundException("Código não encontrado: " + obj.getId());
        }
        TeamAttachment entity = adapter.toEntity(obj);
        return adapter.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
        try{
            repository.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
