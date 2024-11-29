package br.com.projects.domain.business.publico.match.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.match.DMatch;

public interface FindMatch {

    DMatch find (Integer id);
    Paged<DMatch> find(PageableRequest request);
}
