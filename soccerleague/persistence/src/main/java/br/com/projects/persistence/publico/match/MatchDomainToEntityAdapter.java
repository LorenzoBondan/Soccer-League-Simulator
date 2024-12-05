package br.com.projects.persistence.publico.match;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.match.DMatch;
import br.com.projects.domain.business.publico.matchday.DMatchDay;
import br.com.projects.domain.business.publico.matchevent.DMatchEvent;
import br.com.projects.persistence.entities.Match;
import br.com.projects.persistence.entities.MatchDay;
import br.com.projects.persistence.entities.Stadium;
import br.com.projects.persistence.entities.Team;
import br.com.projects.persistence.publico.matchevent.MatchEventDomainToEntityAdapter;
import br.com.projects.persistence.publico.stadium.StadiumDomainToEntityAdapter;
import br.com.projects.persistence.publico.team.TeamDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MatchDomainToEntityAdapter implements Convertable<Match, DMatch> {

    private final MatchEventDomainToEntityAdapter matchEventDomainToEntityAdapter;
    private final TeamDomainToEntityAdapter teamDomainToEntityAdapter;
    private final StadiumDomainToEntityAdapter stadiumDomainToEntityAdapter;

    public MatchDomainToEntityAdapter(MatchEventDomainToEntityAdapter matchEventDomainToEntityAdapter, TeamDomainToEntityAdapter teamDomainToEntityAdapter, StadiumDomainToEntityAdapter stadiumDomainToEntityAdapter) {
        this.matchEventDomainToEntityAdapter = matchEventDomainToEntityAdapter;
        this.teamDomainToEntityAdapter = teamDomainToEntityAdapter;
        this.stadiumDomainToEntityAdapter = stadiumDomainToEntityAdapter;
    }

    @Override
    public Match toEntity(DMatch domain) {
        return Match.builder()
                .id(domain.getId())
                .homeTeam(new Team(domain.getHomeTeam().getId()))
                .awayTeam(new Team(domain.getAwayTeam().getId()))
                .matchDay(new MatchDay(domain.getMatchDay().getId()))
                .stadium(new Stadium(domain.getStadium().getId()))
                .date(domain.getDate())
                .attendees(domain.getAttendees())
                .homeTeamGoals(domain.getHomeTeamGoals())
                .awayTeamGoals(domain.getAwayTeamGoals())
                .build();
    }

    @Override
    public DMatch toDomain(Match entity) {

        List<DMatchEvent> matchEvents = Optional.ofNullable(entity.getMatchEvents())
                .map(lista -> lista.stream()
                        .map(matchEventDomainToEntityAdapter::toDomain)
                        .toList())
                .orElse(List.of());

        return DMatch.builder()
                .id(entity.getId())
                .homeTeam(Optional.ofNullable(entity.getHomeTeam())
                        .map(teamDomainToEntityAdapter::toDomain)
                        .orElse(null))
                .awayTeam(Optional.ofNullable(entity.getAwayTeam())
                        .map(teamDomainToEntityAdapter::toDomain)
                        .orElse(null))
                .matchDay(Optional.ofNullable(entity.getMatchDay())
                        .map(matchDay -> new DMatchDay(
                                matchDay.getId(),
                                Optional.ofNullable(matchDay.getChampionship())
                                        .map(championship -> new DChampionship(championship.getId()))
                                        .orElse(null),
                                matchDay.getNumber(),
                                null
                        ))
                        .orElse(null))
                .stadium(Optional.ofNullable(entity.getStadium())
                        .map(stadiumDomainToEntityAdapter::toDomain)
                        .orElse(null))
                .date(entity.getDate())
                .attendees(entity.getAttendees())
                .homeTeamGoals(entity.getHomeTeamGoals())
                .awayTeamGoals(entity.getAwayTeamGoals())

                .matchEvents(matchEvents)

                .build();
    }
}
