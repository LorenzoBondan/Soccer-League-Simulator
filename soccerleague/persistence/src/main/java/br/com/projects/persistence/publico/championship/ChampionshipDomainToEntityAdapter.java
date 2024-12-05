package br.com.projects.persistence.publico.championship;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.championshipteam.DChampionshipTeam;
import br.com.projects.domain.business.publico.league.DLeague;
import br.com.projects.domain.business.publico.matchday.DMatchDay;
import br.com.projects.domain.business.publico.placing.DPlacing;
import br.com.projects.persistence.entities.Championship;
import br.com.projects.persistence.entities.League;
import br.com.projects.persistence.entities.Season;
import br.com.projects.persistence.publico.championshipteam.ChampionshipTeamDomainToEntityAdapter;
import br.com.projects.persistence.publico.matchday.MatchDayDomainToEntityAdapter;
import br.com.projects.persistence.publico.placing.PlacingDomainToEntityAdapter;
import br.com.projects.persistence.publico.season.SeasonDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ChampionshipDomainToEntityAdapter implements Convertable<Championship, DChampionship> {

    private final PlacingDomainToEntityAdapter placingDomainToEntityAdapter;
    private final ChampionshipTeamDomainToEntityAdapter championshipTeamDomainToEntityAdapter;
    private final MatchDayDomainToEntityAdapter matchDayDomainToEntityAdapter;
    private final SeasonDomainToEntityAdapter seasonDomainToEntityAdapter;

    public ChampionshipDomainToEntityAdapter(PlacingDomainToEntityAdapter placingDomainToEntityAdapter, ChampionshipTeamDomainToEntityAdapter championshipTeamDomainToEntityAdapter, MatchDayDomainToEntityAdapter matchDayDomainToEntityAdapter, SeasonDomainToEntityAdapter seasonDomainToEntityAdapter) {
        this.placingDomainToEntityAdapter = placingDomainToEntityAdapter;
        this.championshipTeamDomainToEntityAdapter = championshipTeamDomainToEntityAdapter;
        this.matchDayDomainToEntityAdapter = matchDayDomainToEntityAdapter;
        this.seasonDomainToEntityAdapter = seasonDomainToEntityAdapter;
    }

    @Override
    public Championship toEntity(DChampionship domain) {
        return Championship.builder()
                .id(domain.getId())
                .league(new League(domain.getLeague().getId()))
                .season(new Season(domain.getSeason().getId()))
                .build();
    }

    @Override
    public DChampionship toDomain(Championship entity) {

        List<DPlacing> placings = Optional.ofNullable(entity.getPlacings())
                .map(lista -> lista.stream()
                        .map(placingDomainToEntityAdapter::toDomain)
                        .toList())
                .orElse(List.of());

        List<DChampionshipTeam> championshipsTeam = Optional.ofNullable(entity.getChampionshipsTeam())
                .map(lista -> lista.stream()
                        .map(championshipTeamDomainToEntityAdapter::toDomain)
                        .toList())
                .orElse(List.of());

        List<DMatchDay> matchDays = Optional.ofNullable(entity.getMatchDays())
                .map(lista -> lista.stream()
                        .map(matchDayDomainToEntityAdapter::toDomain)
                        .toList())
                .orElse(List.of());

        return DChampionship.builder()
                .id(entity.getId())
                .league(Optional.ofNullable(entity.getLeague())
                        .map(league -> new DLeague(league.getId()))
                        .orElse(null))
                .season(Optional.ofNullable(entity.getSeason())
                        .map(seasonDomainToEntityAdapter::toDomain)
                        .orElse(null))

                .placings(placings)
                .championshipTeams(championshipsTeam)
                .matchDays(matchDays)

                .build();
    }
}
