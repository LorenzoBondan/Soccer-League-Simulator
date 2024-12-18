package br.com.projects.domain.business.publico.matchevent.api;

import br.com.projects.domain.business.publico.match.DMatch;
import br.com.projects.domain.business.publico.matchevent.DMatchEvent;
import br.com.projects.domain.business.publico.player.DPlayer;

import java.util.Set;

public interface GenerateRandomMatchEvent {

    DMatchEvent generateRandom (DMatch match, Integer minute, DPlayer player1, DPlayer player2, Set<DPlayer> redCardedPlayers);
}
