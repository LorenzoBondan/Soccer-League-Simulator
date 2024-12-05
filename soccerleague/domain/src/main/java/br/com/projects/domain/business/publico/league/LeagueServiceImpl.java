package br.com.projects.domain.business.publico.league;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.league.api.LeagueService;
import br.com.projects.domain.business.publico.league.spi.CrudLeague;
import br.com.projects.domain.exceptions.RegistroDuplicadoException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class LeagueServiceImpl implements LeagueService {

    private final CrudLeague crudLeague;

    public LeagueServiceImpl(CrudLeague crudLeague) {
        this.crudLeague = crudLeague;
    }

    @Override
    public DLeague find(Integer id) {
        return crudLeague.find(id);
    }

    @Override
    public Paged<DLeague> find(PageableRequest request) {
        return crudLeague.findAll(request);
    }

    @Override
    public DLeague insert(DLeague domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudLeague.insert(domain);
    }

    @Override
    public DLeague update(DLeague domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudLeague.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudLeague.delete(id);
    }

    private void validateDuplicatedResource(DLeague domain){
        if(crudLeague.findByName(domain.getName())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new RegistroDuplicadoException("Verify the field name.");
        }
    }
}
