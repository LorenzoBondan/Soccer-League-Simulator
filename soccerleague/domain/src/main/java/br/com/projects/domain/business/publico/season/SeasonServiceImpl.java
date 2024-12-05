package br.com.projects.domain.business.publico.season;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.season.api.SeasonService;
import br.com.projects.domain.business.publico.season.spi.CrudSeason;
import br.com.projects.domain.exceptions.RegistroDuplicadoException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class SeasonServiceImpl implements SeasonService {

    private final CrudSeason crudSeason;

    public SeasonServiceImpl(CrudSeason crudSeason) {
        this.crudSeason = crudSeason;
    }

    @Override
    public DSeason find(Integer id) {
        return crudSeason.find(id);
    }

    @Override
    public Paged<DSeason> find(PageableRequest request) {
        return crudSeason.findAll(request);
    }

    @Override
    public DSeason insert(DSeason domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudSeason.insert(domain);
    }

    @Override
    public DSeason update(DSeason domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudSeason.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudSeason.delete(id);
    }

    private void validateDuplicatedResource(DSeason domain){
        if(crudSeason.findByYear(domain.getSeasonYear())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new RegistroDuplicadoException("Verify the field year.");
        }
    }
}
