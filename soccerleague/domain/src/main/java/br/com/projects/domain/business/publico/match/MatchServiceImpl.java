package br.com.projects.domain.business.publico.match;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.enums.DMatchEventType;
import br.com.projects.domain.business.publico.match.api.MatchService;
import br.com.projects.domain.business.publico.match.spi.CrudMatch;
import br.com.projects.domain.business.publico.matchevent.DMatchEvent;
import br.com.projects.domain.business.publico.matchevent.api.MatchEventService;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.domain.business.publico.playerstats.DPlayerStats;
import br.com.projects.domain.business.publico.playerstats.api.PlayerStatsService;
import br.com.projects.domain.exceptions.UniqueConstraintViolationException;
import br.com.projects.domain.metadata.DomainService;

import java.util.*;
import java.util.stream.Collectors;

@DomainService
public class MatchServiceImpl implements MatchService {

    private final CrudMatch crudMatch;
    private final MatchEventService matchEventService;
    private final PlayerStatsService playerStatsService;
    private final Random random = new Random();

    public MatchServiceImpl(CrudMatch crudMatch, MatchEventService matchEventService, PlayerStatsService playerStatsService) {
        this.crudMatch = crudMatch;
        this.matchEventService = matchEventService;
        this.playerStatsService = playerStatsService;
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

    @Override
    public DMatch simulate(Integer id) {
        DMatch match = find(id);

        // Seleção de titulares e suplentes
        List<DPlayer> homeTeamStartingPlayers = selectStartingPlayers(match.getHomeTeam().getPlayers());
        List<DPlayer> homeTeamSubstitutePlayers = selectSubstitutePlayers(match.getHomeTeam().getPlayers(), homeTeamStartingPlayers);

        List<DPlayer> awayTeamStartingPlayers = selectStartingPlayers(match.getAwayTeam().getPlayers());
        List<DPlayer> awayTeamSubstitutePlayers = selectSubstitutePlayers(match.getAwayTeam().getPlayers(), awayTeamStartingPlayers);

        Map<DPlayer, Integer> yellowCardCount = new HashMap<>();
        Set<DPlayer> redCardedPlayers = new HashSet<>();
        Set<DPlayer> substitutedPlayers = new HashSet<>();

        for (int minute = 1; minute <= 90; minute += random.nextInt(10) + 1) {
            if (random.nextDouble() < 0.5) continue; // Probabilidade maior de "nada acontecer"

            // Escolhe time aleatoriamente
            boolean isHomeTeam = random.nextBoolean();
            List<DPlayer> eligiblePlayers = isHomeTeam ? homeTeamStartingPlayers : awayTeamStartingPlayers;

            // Filtra jogadores disponíveis
            List<DPlayer> availablePlayers = eligiblePlayers.stream()
                    .filter(player -> !redCardedPlayers.contains(player))
                    .filter(player -> !substitutedPlayers.contains(player))
                    .collect(Collectors.toList());

            if (availablePlayers.size() < 2) continue;

            DPlayer player1 = availablePlayers.get(random.nextInt(availablePlayers.size()));
            DPlayer player2 = availablePlayers.stream()
                    .filter(player -> !player.equals(player1))
                    .collect(Collectors.toList())
                    .get(random.nextInt(availablePlayers.size() - 1));

            DMatchEvent event = matchEventService.generateRandom(match, minute, player1, player2, redCardedPlayers);

            if (event != null) {
                processMatchEvent(match, event, yellowCardCount, redCardedPlayers, substitutedPlayers,
                        homeTeamStartingPlayers, homeTeamSubstitutePlayers,
                        awayTeamStartingPlayers, awayTeamSubstitutePlayers);
            }
        }

        return update(match);
    }

    private List<DPlayer> selectStartingPlayers(List<DPlayer> players) {
        return players.stream()
                .limit(11) // Seleciona os 11 primeiros como titulares
                .collect(Collectors.toList());
    }

    private List<DPlayer> selectSubstitutePlayers(List<DPlayer> players, List<DPlayer> startingPlayers) {
        return players.stream()
                .filter(player -> !startingPlayers.contains(player)) // Exclui titulares
                .limit(5) // Seleciona até 5 suplentes
                .collect(Collectors.toList());
    }

    private void processMatchEvent(
            DMatch match, DMatchEvent event, Map<DPlayer, Integer> yellowCardCount,
            Set<DPlayer> redCardedPlayers, Set<DPlayer> substitutedPlayers,
            List<DPlayer> homeTeamStartingPlayers, List<DPlayer> homeTeamSubstitutePlayers,
            List<DPlayer> awayTeamStartingPlayers, List<DPlayer> awayTeamSubstitutePlayers) {

        DPlayerStats playerStats = playerStatsService.findByPlayerAndChampionship(event.getPlayer1().getId(), match.getMatchDay().getChampionship().getId()).iterator().next();
        if (event.getType() == DMatchEventType.YELLOW_CARD) {
            yellowCardCount.put(event.getPlayer1(), yellowCardCount.getOrDefault(event.getPlayer1(), 0) + 1);
            playerStats.setYellowCards(playerStats.getYellowCards() + 1);
            if (yellowCardCount.get(event.getPlayer1()) == 2) {
                redCardedPlayers.add(event.getPlayer1());
                matchEventService.insert(createRedCardEvent(match, event.getMinuteMatchEvent(), event.getPlayer1()));
                playerStats.setRedCards(playerStats.getRedCards() + 1);
            }
        } else if (event.getType() == DMatchEventType.RED_CARD) {
            redCardedPlayers.add(event.getPlayer1());
            playerStats.setRedCards(playerStats.getRedCards() + 1);
        } else if (event.getType() == DMatchEventType.SUBSTITUTION) {
            substitutedPlayers.add(event.getPlayer1());
            handleSubstitution(event.getPlayer1(), event.getPlayer2(), homeTeamStartingPlayers, homeTeamSubstitutePlayers, awayTeamStartingPlayers, awayTeamSubstitutePlayers);
        }

        playerStatsService.update(playerStats);
        matchEventService.insert(event);
        updateMatchScores(match, event, playerStats);
    }

    private void handleSubstitution(
            DPlayer outPlayer, DPlayer inPlayer,
            List<DPlayer> homeTeamStartingPlayers, List<DPlayer> homeTeamSubstitutePlayers,
            List<DPlayer> awayTeamStartingPlayers, List<DPlayer> awayTeamSubstitutePlayers) {

        if (homeTeamStartingPlayers.contains(outPlayer)) {
            homeTeamStartingPlayers.remove(outPlayer);
            homeTeamStartingPlayers.add(inPlayer);
            homeTeamSubstitutePlayers.remove(inPlayer);
        } else if (awayTeamStartingPlayers.contains(outPlayer)) {
            awayTeamStartingPlayers.remove(outPlayer);
            awayTeamStartingPlayers.add(inPlayer);
            awayTeamSubstitutePlayers.remove(inPlayer);
        }
    }

    private void updateMatchScores(DMatch match, DMatchEvent event, DPlayerStats playerStats) {
        if (event.getType() == DMatchEventType.GOAL) {
            if (random.nextBoolean()) {
                match.setHomeTeamGoals(match.getHomeTeamGoals() + 1);
            } else {
                match.setAwayTeamGoals(match.getAwayTeamGoals() + 1);
            }
            playerStats.setGoals(playerStats.getGoals() + 1);
            playerStatsService.update(playerStats);
        }
    }

    private DMatchEvent createRedCardEvent(DMatch match, int minute, DPlayer player) {
        return DMatchEvent.builder()
                .match(match)
                .player1(player)
                .minuteMatchEvent(minute)
                .description("Automatic red card after two yellows")
                .type(DMatchEventType.RED_CARD)
                .build();
    }

    private void validateDuplicatedResource(DMatch domain){
        if(crudMatch.findByHomeTeamAndAwayTeamAndMatchDay(domain.getHomeTeam().getId(), domain.getAwayTeam().getId(), domain.getMatchDay().getId())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new UniqueConstraintViolationException("Duplicated resource for the combination of Home Team, Away Team and MatchDay.");
        }
    }
}
