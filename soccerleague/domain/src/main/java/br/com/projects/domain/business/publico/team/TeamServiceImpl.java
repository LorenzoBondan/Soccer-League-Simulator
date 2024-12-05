package br.com.projects.domain.business.publico.team;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.team.api.TeamService;
import br.com.projects.domain.business.publico.team.spi.CrudTeam;
import br.com.projects.domain.exceptions.RegistroDuplicadoException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class TeamServiceImpl implements TeamService {

    private final CrudTeam crudTeam;

    public TeamServiceImpl(CrudTeam crudTeam) {
        this.crudTeam = crudTeam;
    }

    @Override
    public DTeam find(Integer id) {
        return crudTeam.find(id);
    }

    @Override
    public Paged<DTeam> find(PageableRequest request) {
        return crudTeam.findAll(request);
    }

    @Override
    public DTeam insert(DTeam domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTeam.insert(domain);
    }

    @Override
    public DTeam update(DTeam domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTeam.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudTeam.delete(id);
    }

    private void validateDuplicatedResource(DTeam domain){
        if(crudTeam.findByName(domain.getName())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new RegistroDuplicadoException("Verify the field name.");
        }
    }
}
