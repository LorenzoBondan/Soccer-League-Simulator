package br.com.projects.domain.business.publico.matchevent.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.matchevent.DMatchEvent;

public interface FindMatchEvent {

    DMatchEvent find (Integer id);
    Paged<DMatchEvent> find (PageableRequest request);
}
