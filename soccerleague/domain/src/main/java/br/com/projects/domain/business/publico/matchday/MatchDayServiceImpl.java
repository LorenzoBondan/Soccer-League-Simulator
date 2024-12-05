package br.com.projects.domain.business.publico.matchday;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.matchday.api.MatchDayService;
import br.com.projects.domain.business.publico.matchday.spi.CrudMatchDay;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class MatchDayServiceImpl implements MatchDayService {

    private final CrudMatchDay crudMatchDay;

    public MatchDayServiceImpl(CrudMatchDay crudMatchDay) {
        this.crudMatchDay = crudMatchDay;
    }

    @Override
    public DMatchDay find(Integer id) {
        return crudMatchDay.find(id);
    }

    @Override
    public Paged<DMatchDay> find(PageableRequest request) {
        return crudMatchDay.findAll(request);
    }

    @Override
    public DMatchDay insert(DMatchDay domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudMatchDay.insert(domain);
    }

    @Override
    public DMatchDay update(DMatchDay domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudMatchDay.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudMatchDay.delete(id);
    }

    private void validateDuplicatedResource(DMatchDay domain){
        if(crudMatchDay.findByChampionshipAndNumber(domain.getChampionship().getId(), domain.getNumber())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Championship and Number.");
        }
    }
}
