package br.com.projects.domain.business.publico.championship;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.championship.api.ChampionshipService;
import br.com.projects.domain.business.publico.championship.spi.CrudChampionship;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class ChampionshipServiceImpl implements ChampionshipService {

    private final CrudChampionship crudChampionship;

    public ChampionshipServiceImpl(CrudChampionship crudChampionship) {
        this.crudChampionship = crudChampionship;
    }

    @Override
    public DChampionship find(Integer id) {
        return crudChampionship.find(id);
    }

    @Override
    public Paged<DChampionship> find(PageableRequest request) {
        return crudChampionship.findAll(request);
    }

    @Override
    public DChampionship insert(DChampionship domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudChampionship.insert(domain);
    }

    @Override
    public DChampionship update(DChampionship domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudChampionship.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudChampionship.delete(id);
    }

    private void validateDuplicatedResource(DChampionship domain){
        if(crudChampionship.findByLeagueAndSeason(domain.getLeague().getId(), domain.getSeason().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of League and Season.");
        }
    }
}
