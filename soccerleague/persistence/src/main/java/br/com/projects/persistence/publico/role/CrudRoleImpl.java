package br.com.projects.persistence.publico.role;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.PagedBuilder;
import br.com.projects.domain.business.publico.role.DRole;
import br.com.projects.domain.business.publico.role.spi.CrudRole;
import br.com.projects.domain.exceptions.ResourceNotFoundException;
import br.com.projects.persistence.entities.Role;
import br.com.projects.persistence.util.PageRequestUtils;
import br.com.projects.persistence.util.SpecificationHelper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CrudRoleImpl implements CrudRole {

    private final RoleRepository repository;
    private final RoleQueryRepository queryRepository;
    private final RoleDomainToEntityAdapter adapter;
    private final PageRequestUtils pageRequestUtils;

    public CrudRoleImpl(RoleRepository repository, RoleQueryRepository queryRepository, RoleDomainToEntityAdapter adapter, PageRequestUtils pageRequestUtils) {
        this.repository = repository;
        this.queryRepository = queryRepository;
        this.adapter = adapter;
        this.pageRequestUtils = pageRequestUtils;
    }

    @Override
    @Transactional(readOnly = true)
    public Paged<DRole> buscarTodos(PageableRequest request) {
        SpecificationHelper<Role> helper = new SpecificationHelper<>();
        Specification<Role> specification = helper.buildSpecification(request.getColunas(), request.getOperacoes(), request.getValores());
        
        return Optional.of(queryRepository.findAll(specification, pageRequestUtils.toPage(request)))
                .map(r -> new PagedBuilder<DRole>()
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
    public DRole inserir(DRole obj) {
        return null;
    }

    @Override
    public DRole atualizar(DRole obj) {
        return null;
    }

    @Override
    public void remover(Integer obj) {

    }

    @Override
    @Transactional(readOnly = true)
    public DRole buscar(Integer id) {
        return adapter.toDomain(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Código não encontrado: " + id)));
    }
}