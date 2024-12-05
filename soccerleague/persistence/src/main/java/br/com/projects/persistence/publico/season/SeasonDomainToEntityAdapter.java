package br.com.projects.persistence.publico.season;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.season.DSeason;
import br.com.projects.persistence.entities.Season;
import org.springframework.stereotype.Component;

@Component
public class SeasonDomainToEntityAdapter implements Convertable<Season, DSeason> {

    @Override
    public Season toEntity(DSeason domain) {
        return Season.builder()
                .id(domain.getId())
                .seasonYear(domain.getSeasonYear())
                .build();
    }

    @Override
    public DSeason toDomain(Season entity) {
        return DSeason.builder()
                .id(entity.getId())
                .seasonYear(entity.getSeasonYear())
                .build();
    }
}
