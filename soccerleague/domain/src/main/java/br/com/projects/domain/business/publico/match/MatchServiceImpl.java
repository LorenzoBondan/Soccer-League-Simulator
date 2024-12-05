package br.com.projects.domain.business.publico.match;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.match.api.MatchService;
import br.com.projects.domain.business.publico.match.spi.CrudMatch;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class MatchServiceImpl implements MatchService {

    private final CrudMatch crudMatch;

    public MatchServiceImpl(CrudMatch crudMatch) {
        this.crudMatch = crudMatch;
    }

    @Override
    public DMatch find(Integer id) {
        return crudMatch.find(id);
    }

    @Override
    public Paged<DMatch> find(PageableRequest request) {
        return crudMatch.findAll(request);
    }

    @Override
    public DMatch insert(DMatch domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudMatch.insert(domain);
    }

    @Override
    public DMatch update(DMatch domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudMatch.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudMatch.delete(id);
    }

    private void validateDuplicatedResource(DMatch domain){
        if(crudMatch.findByHomeTeamAndAwayTeamAndMatchDay(domain.getHomeTeam().getId(), domain.getAwayTeam().getId(), domain.getMatchDay().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Home Team, Away Team and MatchDay.");
        }
    }
}
