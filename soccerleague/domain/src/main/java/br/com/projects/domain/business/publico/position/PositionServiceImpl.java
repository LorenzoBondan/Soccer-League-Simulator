package br.com.projects.domain.business.publico.position;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.position.api.PositionService;
import br.com.projects.domain.business.publico.position.spi.CrudPosition;
import br.com.projects.domain.metadata.DomainService;

@DomainService
public class PositionServiceImpl implements PositionService {

    private final CrudPosition crudPosition;

    public PositionServiceImpl(CrudPosition crudPosition) {
        this.crudPosition = crudPosition;
    }

    @Override
    public Paged<DPosition> find(PageableRequest request) {
        return crudPosition.findAll(request);
    }

    @Override
    public DPosition find(Integer id) {
        return crudPosition.find(id);
    }
}
