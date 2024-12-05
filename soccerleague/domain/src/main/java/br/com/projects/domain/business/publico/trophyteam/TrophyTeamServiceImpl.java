package br.com.projects.domain.business.publico.trophyteam;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.trophyteam.api.TrophyTeamService;
import br.com.projects.domain.business.publico.trophyteam.spi.CrudTrophyTeam;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class TrophyTeamServiceImpl implements TrophyTeamService {

    private final CrudTrophyTeam crudTrophyTeam;

    public TrophyTeamServiceImpl(CrudTrophyTeam crudTrophyTeam) {
        this.crudTrophyTeam = crudTrophyTeam;
    }

    @Override
    public DTrophyTeam find(Integer id) {
        return crudTrophyTeam.find(id);
    }

    @Override
    public Paged<DTrophyTeam> find(PageableRequest request) {
        return crudTrophyTeam.findAll(request);
    }

    @Override
    public DTrophyTeam insert(DTrophyTeam domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTrophyTeam.insert(domain);
    }

    @Override
    public DTrophyTeam update(DTrophyTeam domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudTrophyTeam.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudTrophyTeam.delete(id);
    }

    private void validateDuplicatedResource(DTrophyTeam domain){
        if(crudTrophyTeam.findByTrophyAndTeam(domain.getTrophy().getId(), domain.getTeam().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Trophy and Team.");
        }
    }
}
