package br.com.projects.persistence.publico.stadiumattachment;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.PagedBuilder;
import br.com.projects.domain.business.publico.stadiumattachment.DStadiumAttachment;
import br.com.projects.domain.business.publico.stadiumattachment.spi.CrudStadiumAttachment;
import br.com.projects.domain.exceptions.DatabaseException;
import br.com.projects.domain.exceptions.ResourceNotFoundException;
import br.com.projects.persistence.entities.StadiumAttachment;
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
public class CrudStadiumAttachmentImpl implements CrudStadiumAttachment {

    private final StadiumAttachmentRepository repository;
    private final StadiumAttachmentQueryRepository queryRepository;
    private final StadiumAttachmentDomainToEntityAdapter adapter;
    private final PageRequestUtils pageRequestUtils;

    public CrudStadiumAttachmentImpl(StadiumAttachmentRepository repository, StadiumAttachmentQueryRepository queryRepository, StadiumAttachmentDomainToEntityAdapter adapter, PageRequestUtils pageRequestUtils) {
        this.repository = repository;
        this.queryRepository = queryRepository;
        this.adapter = adapter;
        this.pageRequestUtils = pageRequestUtils;
    }

    @Override
    @Transactional(readOnly = true)
    public Paged<DStadiumAttachment> findAll(PageableRequest request) {
        SpecificationHelper<StadiumAttachment> helper = new SpecificationHelper<>();
        Specification<StadiumAttachment> specification = helper.buildSpecification(request.getColunas(), request.getOperacoes(), request.getValores());

        return Optional.of(queryRepository.findAll(specification, pageRequestUtils.toPage(request)))
                .map(r -> new PagedBuilder<DStadiumAttachment>()
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
    public Collection<? extends DStadiumAttachment> findByStadiumAndAttachment(Integer stadiumId, Integer attachmentId) {
        return queryRepository.findByStadium_IdAndAttachment_Id(stadiumId, attachmentId).stream().map(adapter::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DStadiumAttachment find(Integer id) {
        return adapter.toDomain(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Código não encontrado: " + id)));
    }

    @Override
    @Transactional
    public DStadiumAttachment insert(DStadiumAttachment obj) {
        return adapter.toDomain(repository.save(adapter.toEntity(obj)));
    }

    @Override
    @Transactional
    public DStadiumAttachment update(DStadiumAttachment obj) {
        if(!repository.existsById(obj.getId())){
            throw new ResourceNotFoundException("Código não encontrado: " + obj.getId());
        }
        StadiumAttachment entity = adapter.toEntity(obj);
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
