package br.com.projects.domain.business.publico.binary.api;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.binary.DBinary;

public interface FindBinary {

    DBinary find (Integer id);
    Paged<DBinary> find (PageableRequest request);
}
