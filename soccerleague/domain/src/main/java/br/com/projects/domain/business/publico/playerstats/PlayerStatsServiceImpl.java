package br.com.projects.domain.business.publico.playerstats;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.playerstats.api.PlayerStatsService;
import br.com.projects.domain.business.publico.playerstats.spi.CrudPlayerStats;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class PlayerStatsServiceImpl implements PlayerStatsService {

    private final CrudPlayerStats crudPlayerStats;

    public PlayerStatsServiceImpl(CrudPlayerStats crudPlayerStats) {
        this.crudPlayerStats = crudPlayerStats;
    }

    @Override
    public DPlayerStats find(Integer id) {
        return crudPlayerStats.find(id);
    }

    @Override
    public Paged<DPlayerStats> find(PageableRequest request) {
        return crudPlayerStats.findAll(request);
    }

    @Override
    public DPlayerStats insert(DPlayerStats domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudPlayerStats.insert(domain);
    }

    @Override
    public DPlayerStats update(DPlayerStats domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudPlayerStats.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudPlayerStats.delete(id);
    }

    private void validateDuplicatedResource(DPlayerStats domain){
        if(crudPlayerStats.findByPlayerAndChampionship(domain.getPlayer().getId(), domain.getChampionship().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Player and Championship.");
        }
    }
}
