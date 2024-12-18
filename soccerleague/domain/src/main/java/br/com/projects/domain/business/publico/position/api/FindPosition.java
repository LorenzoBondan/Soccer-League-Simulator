package br.com.projects.domain.business.publico.position.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.position.DPosition;

public interface FindPosition {

    DPosition find (Integer id);
    Paged<DPosition> find(PageableRequest request);
}
