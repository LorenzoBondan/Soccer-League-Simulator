package br.com.projects.domain.business.publico.matchevent;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.matchevent.api.MatchEventService;
import br.com.projects.domain.business.publico.matchevent.spi.CrudMatchEvent;
import br.com.projects.domain.metadata.DomainService;

@DomainService
public class MatchEventServiceImpl implements MatchEventService {

    private final CrudMatchEvent crudMatchEvent;

    public MatchEventServiceImpl(CrudMatchEvent crudMatchEvent) {
        this.crudMatchEvent = crudMatchEvent;
    }

    @Override
    public DMatchEvent find(Integer id) {
        return crudMatchEvent.find(id);
    }

    @Override
    public Paged<DMatchEvent> find(PageableRequest request) {
        return crudMatchEvent.findAll(request);
    }

    @Override
    public DMatchEvent insert(DMatchEvent domain) {
        domain.validate();
        return crudMatchEvent.insert(domain);
    }

    @Override
    public DMatchEvent update(DMatchEvent domain) {
        domain.validate();
        return crudMatchEvent.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudMatchEvent.delete(id);
    }
}
