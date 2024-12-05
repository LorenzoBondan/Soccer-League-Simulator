package br.com.projects.persistence.publico.matchevent;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.enums.DMatchEventType;
import br.com.projects.domain.business.publico.match.DMatch;
import br.com.projects.domain.business.publico.matchevent.DMatchEvent;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.persistence.entities.Match;
import br.com.projects.persistence.entities.MatchEvent;
import br.com.projects.persistence.entities.Player;
import br.com.projects.persistence.entities.enums.MatchEventType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MatchEventDomainToEntityAdapter implements Convertable<MatchEvent, DMatchEvent> {

    @Override
    public MatchEvent toEntity(DMatchEvent domain) {
        return MatchEvent.builder()
                .id(domain.getId())
                .match(new Match(domain.getMatch().getId()))
                .type(MatchEventType.valueOf(domain.getType().name()))
                .description(domain.getDescription())
                .player1(new Player(domain.getPlayer1().getId()))
                .player2(Optional.ofNullable(domain.getPlayer2())
                        .map(player -> new Player(player.getId()))
                        .orElse(null))
                .minuteMatchEvent(domain.getMinuteMatchEvent())
                .build();
    }

    @Override
    public DMatchEvent toDomain(MatchEvent entity) {
        return DMatchEvent.builder()
                .id(entity.getId())
                .match(Optional.ofNullable(entity.getMatch())
                        .map(match -> new DMatch(match.getId()))
                        .orElse(null))
                .type(Optional.ofNullable(entity.getType())
                        .map(type -> DMatchEventType.valueOf(type.name()))
                        .orElse(null))
                .description(entity.getDescription())
                .player1(Optional.ofNullable(entity.getPlayer1())
                        .map(player -> new DPlayer(player.getId()))
                        .orElse(null))
                .player2(Optional.ofNullable(entity.getPlayer2())
                        .map(player -> new DPlayer(player.getId()))
                        .orElse(null))
                .minuteMatchEvent(entity.getMinuteMatchEvent())
                .build();
    }
}
