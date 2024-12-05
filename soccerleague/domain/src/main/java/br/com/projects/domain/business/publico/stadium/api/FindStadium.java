package br.com.projects.domain.business.publico.stadium.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.stadium.DStadium;

public interface FindStadium {

    DStadium find (Integer id);
    Paged<DStadium> find (PageableRequest request);
}
