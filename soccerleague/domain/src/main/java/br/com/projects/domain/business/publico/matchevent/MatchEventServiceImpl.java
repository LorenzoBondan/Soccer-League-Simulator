package br.com.projects.domain.business.publico.matchevent;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.enums.DMatchEventType;
import br.com.projects.domain.business.publico.match.DMatch;
import br.com.projects.domain.business.publico.matchevent.api.MatchEventService;
import br.com.projects.domain.business.publico.matchevent.spi.CrudMatchEvent;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.domain.metadata.DomainService;

import java.util.Random;
import java.util.Set;

@DomainService
public class MatchEventServiceImpl implements MatchEventService {

    private final CrudMatchEvent crudMatchEvent;
    private final Random random = new Random();

    public MatchEventServiceImpl(CrudMatchEvent crudMatchEvent) {
        this.crudMatchEvent = crudMatchEvent;
    }

    @Override
    public DMatchEvent find(Integer id) {
        return crudMatchEvent.find(id);
    }

    @Override
    public Paged<DMatchEvent> find(PageableRequest request) {
        return crudMatchEvent.findAll(request);
    }

    @Override
    public DMatchEvent insert(DMatchEvent domain) {
        domain.validate();
        return crudMatchEvent.insert(domain);
    }

    @Override
    public DMatchEvent update(DMatchEvent domain) {
        domain.validate();
        return crudMatchEvent.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudMatchEvent.delete(id);
    }

    @Override
    public DMatchEvent generateRandom(DMatch match, Integer minute, DPlayer player1, DPlayer player2, Set<DPlayer> redCardedPlayers) {
        DMatchEventType[] types = DMatchEventType.values();
        DMatchEventType type = types[random.nextInt(types.length)];

        String description = switch (type) {
            case GOAL -> "Goal scored";
            case YELLOW_CARD -> "Yellow card given";
            case RED_CARD -> "Red card given";
            case SUBSTITUTION -> "Player substituted";
        };

        DMatchEvent.DMatchEventBuilder eventBuilder = DMatchEvent.builder()
                .match(match)
                .player1(player1)
                .minuteMatchEvent(minute)
                .description(description)
                .type(type);

        if (type == DMatchEventType.SUBSTITUTION) {
            eventBuilder.player2(player2);
        }

        return crudMatchEvent.insert(eventBuilder.build());
    }
}
