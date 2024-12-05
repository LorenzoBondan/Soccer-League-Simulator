package br.com.projects.domain.business.publico.trophy;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.trophy.api.TrophyService;
import br.com.projects.domain.business.publico.trophy.spi.CrudTrophy;
import br.com.projects.domain.exceptions.RegistroDuplicadoException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class TrophyServiceImpl implements TrophyService {

    private final CrudTrophy crudTrophy;

    public TrophyServiceImpl(CrudTrophy crudTrophy) {
        this.crudTrophy = crudTrophy;
    }

    @Override
    public DTrophy find(Integer id) {
        return crudTrophy.find(id);
    }

    @Override
    public Paged<DTrophy> find(PageableRequest request) {
        return crudTrophy.findAll(request);
    }

    @Override
    public DTrophy insert(DTrophy domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTrophy.insert(domain);
    }

    @Override
    public DTrophy update(DTrophy domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTrophy.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudTrophy.delete(id);
    }

    private void validateDuplicatedResource(DTrophy domain){
        if(crudTrophy.findByName(domain.getName())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new RegistroDuplicadoException("Verify the field name.");
        }
    }
}
