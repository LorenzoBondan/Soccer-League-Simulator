package br.com.projects.domain.business.publico.championshipteam;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.championshipteam.api.ChampionshipTeamService;
import br.com.projects.domain.business.publico.championshipteam.spi.CrudChampionshipTeam;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class ChampionshipTeamServiceImpl implements ChampionshipTeamService {

    private final CrudChampionshipTeam crudChampionshipTeam;

    public ChampionshipTeamServiceImpl(CrudChampionshipTeam crudChampionshipTeam) {
        this.crudChampionshipTeam = crudChampionshipTeam;
    }

    @Override
    public DChampionshipTeam find(Integer id) {
        return crudChampionshipTeam.find(id);
    }

    @Override
    public Paged<DChampionshipTeam> find(PageableRequest request) {
        return crudChampionshipTeam.findAll(request);
    }

    @Override
    public DChampionshipTeam insert(DChampionshipTeam domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudChampionshipTeam.insert(domain);
    }

    @Override
    public DChampionshipTeam update(DChampionshipTeam domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudChampionshipTeam.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudChampionshipTeam.delete(id);
    }

    private void validateDuplicatedResource(DChampionshipTeam domain){
        if(crudChampionshipTeam.findByChampionshipAndTeam(domain.getChampionship().getId(), domain.getTeam().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Championship and Team.");
        }
    }
}
