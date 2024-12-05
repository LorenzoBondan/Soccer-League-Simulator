package br.com.projects.persistence.publico.placing;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.placing.DPlacing;
import br.com.projects.persistence.entities.Championship;
import br.com.projects.persistence.entities.Placing;
import br.com.projects.persistence.entities.Team;
import br.com.projects.persistence.publico.team.TeamDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlacingDomainToEntityAdapter implements Convertable<Placing, DPlacing> {

    private final TeamDomainToEntityAdapter teamDomainToEntityAdapter;

    public PlacingDomainToEntityAdapter(TeamDomainToEntityAdapter teamDomainToEntityAdapter) {
        this.teamDomainToEntityAdapter = teamDomainToEntityAdapter;
    }

    @Override
    public Placing toEntity(DPlacing domain) {
        return Placing.builder()
                .id(domain.getId())
                .championship(new Championship(domain.getChampionship().getId()))
                .team(new Team(domain.getTeam().getId()))
                .points(domain.getPoints())
                .victories(domain.getVictories())
                .draws(domain.getDraws())
                .defeats(domain.getDefeats())
                .goalsScored(domain.getGoalsScored())
                .goalsConceded(domain.getGoalsConceded())
                .goalDifference(domain.getGoalDifference())
                .build();
    }

    @Override
    public DPlacing toDomain(Placing entity) {
        return DPlacing.builder()
                .id(entity.getId())
                .championship(Optional.ofNullable(entity.getChampionship())
                        .map(championship -> new DChampionship(championship.getId()))
                        .orElse(null))
                .team(Optional.ofNullable(entity.getTeam())
                        .map(teamDomainToEntityAdapter::toDomain)
                        .orElse(null))
                .points(entity.getPoints())
                .victories(entity.getVictories())
                .draws(entity.getDraws())
                .defeats(entity.getDefeats())
                .goalsScored(entity.getGoalsScored())
                .goalsConceded(entity.getGoalsConceded())
                .goalDifference(entity.getGoalDifference())
                .build();
    }
}
