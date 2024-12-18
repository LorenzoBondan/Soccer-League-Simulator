package br.com.projects.persistence.publico.position;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.PagedBuilder;
import br.com.projects.domain.business.publico.position.DPosition;
import br.com.projects.domain.business.publico.position.spi.CrudPosition;
import br.com.projects.domain.exceptions.ResourceNotFoundException;
import br.com.projects.persistence.entities.Position;
import br.com.projects.persistence.util.PageRequestUtils;
import br.com.projects.persistence.util.SpecificationHelper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CrudPositionImpl implements CrudPosition {

    private final PositionRepository repository;
    private final PositionQueryRepository queryRepository;
    private final PositionDomainToEntityAdapter adapter;
    private final PageRequestUtils pageRequestUtils;

    public CrudPositionImpl(PositionRepository repository, PositionQueryRepository queryRepository, PositionDomainToEntityAdapter adapter, PageRequestUtils pageRequestUtils) {
        this.repository = repository;
        this.queryRepository = queryRepository;
        this.adapter = adapter;
        this.pageRequestUtils = pageRequestUtils;
    }

    @Override
    @Transactional(readOnly = true)
    public Paged<DPosition> findAll(PageableRequest request) {
        SpecificationHelper<Position> helper = new SpecificationHelper<>();
        Specification<Position> specification = helper.buildSpecification(request.getColunas(), request.getOperacoes(), request.getValores());
        
        return Optional.of(queryRepository.findAll(specification, pageRequestUtils.toPage(request)))
                .map(r -> new PagedBuilder<DPosition>()
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
    public DPosition find(Integer id) {
        return adapter.toDomain(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Código não encontrado: " + id)));
    }

    @Override
    public DPosition insert(DPosition obj) {
        return null;
    }

    @Override
    public DPosition update(DPosition obj) {
        return null;
    }

    @Override
    public void delete(Integer obj) {

    }
}
