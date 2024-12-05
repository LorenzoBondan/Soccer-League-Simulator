package br.com.projects.persistence.publico.championshipteam;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.championshipteam.DChampionshipTeam;
import br.com.projects.domain.business.publico.team.DTeam;
import br.com.projects.persistence.entities.Championship;
import br.com.projects.persistence.entities.ChampionshipTeam;
import br.com.projects.persistence.entities.Team;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChampionshipTeamDomainToEntityAdapter implements Convertable<ChampionshipTeam, DChampionshipTeam> {

    @Override
    public ChampionshipTeam toEntity(DChampionshipTeam domain) {
        return ChampionshipTeam.builder()
                .id(domain.getId())
                .championship(new Championship(domain.getChampionship().getId()))
                .team(new Team(domain.getTeam().getId()))
                .build();
    }

    @Override
    public DChampionshipTeam toDomain(ChampionshipTeam entity) {
        return DChampionshipTeam.builder()
                .id(entity.getId())
                .championship(Optional.ofNullable(entity.getChampionship())
                        .map(championship -> new DChampionship(championship.getId()))
                        .orElse(null))
                .team(Optional.ofNullable(entity.getTeam())
                        .map(team -> new DTeam(team.getId()))
                        .orElse(null))
                .build();
    }
}
