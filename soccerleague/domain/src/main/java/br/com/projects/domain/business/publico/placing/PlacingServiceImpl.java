package br.com.projects.domain.business.publico.placing;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.placing.api.PlacingService;
import br.com.projects.domain.business.publico.placing.spi.CrudPlacing;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class PlacingServiceImpl implements PlacingService {

    private final CrudPlacing crudPlacing;

    public PlacingServiceImpl(CrudPlacing crudPlacing) {
        this.crudPlacing = crudPlacing;
    }

    @Override
    public DPlacing find(Integer id) {
        return crudPlacing.find(id);
    }

    @Override
    public Paged<DPlacing> find(PageableRequest request) {
        return crudPlacing.findAll(request);
    }

    @Override
    public DPlacing insert(DPlacing domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudPlacing.insert(domain);
    }

    @Override
    public DPlacing update(DPlacing domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudPlacing.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudPlacing.delete(id);
    }

    private void validateDuplicatedResource(DPlacing domain){
        if(crudPlacing.findByChampionshipAndTeam(domain.getChampionship().getId(), domain.getTeam().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Championship and Team.");
        }
    }
}
