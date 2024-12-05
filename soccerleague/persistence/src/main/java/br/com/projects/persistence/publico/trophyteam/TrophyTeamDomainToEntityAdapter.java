package br.com.projects.persistence.publico.trophyteam;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.team.DTeam;
import br.com.projects.domain.business.publico.trophy.DTrophy;
import br.com.projects.domain.business.publico.trophyteam.DTrophyTeam;
import br.com.projects.persistence.entities.Team;
import br.com.projects.persistence.entities.Trophy;
import br.com.projects.persistence.entities.TrophyTeam;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrophyTeamDomainToEntityAdapter implements Convertable<TrophyTeam, DTrophyTeam> {

    @Override
    public TrophyTeam toEntity(DTrophyTeam domain) {
        return TrophyTeam.builder()
                .id(domain.getId())
                .trophy(new Trophy(domain.getTrophy().getId()))
                .team(new Team(domain.getTeam().getId()))
                .quantity(domain.getQuantity())
                .build();
    }

    @Override
    public DTrophyTeam toDomain(TrophyTeam entity) {
        return DTrophyTeam.builder()
                .id(entity.getId())
                .trophy(Optional.ofNullable(entity.getTrophy())
                        .map(Trophy -> new DTrophy(Trophy.getId()))
                        .orElse(null))
                .team(Optional.ofNullable(entity.getTeam())
                        .map(Team -> new DTeam(Team.getId()))
                        .orElse(null))
                .quantity(entity.getQuantity())
                .build();
    }
}
