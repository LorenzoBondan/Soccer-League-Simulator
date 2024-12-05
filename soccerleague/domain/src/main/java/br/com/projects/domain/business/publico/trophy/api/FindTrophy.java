package br.com.projects.domain.business.publico.trophy.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.trophy.DTrophy;

public interface FindTrophy {

    DTrophy find (Integer id);
    Paged<DTrophy> find (PageableRequest request);
}
