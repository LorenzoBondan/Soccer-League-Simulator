package br.com.projects.domain.business.publico.role;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.role.api.RoleService;
import br.com.projects.domain.business.publico.role.spi.CrudRole;
import br.com.projects.domain.metadata.DomainService;

@DomainService
public class RoleServiceImpl implements RoleService {

    private final CrudRole crudRole;

    public RoleServiceImpl(CrudRole crudRole) {
        this.crudRole = crudRole;
    }

    @Override
    public Paged<DRole> find(PageableRequest request) {
        return crudRole.buscarTodos(request);
    }

    @Override
    public DRole find(Integer id) {
        return crudRole.buscar(id);
    }
}
