package br.com.projects.persistence.publico.matchday;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.match.DMatch;
import br.com.projects.domain.business.publico.matchday.DMatchDay;
import br.com.projects.persistence.entities.Championship;
import br.com.projects.persistence.entities.MatchDay;
import br.com.projects.persistence.publico.match.MatchDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MatchDayDomainToEntityAdapter implements Convertable<MatchDay, DMatchDay> {

    private final MatchDomainToEntityAdapter matchDomainToEntityAdapter;

    public MatchDayDomainToEntityAdapter(MatchDomainToEntityAdapter matchDomainToEntityAdapter) {
        this.matchDomainToEntityAdapter = matchDomainToEntityAdapter;
    }

    @Override
    public MatchDay toEntity(DMatchDay domain) {
        return MatchDay.builder()
                .id(domain.getId())
                .championship(new Championship(domain.getChampionship().getId()))
                .number(domain.getNumber())
                .build();
    }

    @Override
    public DMatchDay toDomain(MatchDay entity) {

        List<DMatch> matches = Optional.ofNullable(entity.getMatches())
                .map(lista -> lista.stream()
                        .map(matchDomainToEntityAdapter::toDomain)
                        .toList())
                .orElse(List.of());

        return DMatchDay.builder()
                .id(entity.getId())
                .championship(Optional.ofNullable(entity.getChampionship())
                        .map(championship -> new DChampionship(championship.getId()))
                        .orElse(null))

                .matches(matches)

                .build();
    }
}
