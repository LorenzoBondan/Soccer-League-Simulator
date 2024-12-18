package br.com.projects.persistence.publico.playerstats;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.domain.business.publico.playerstats.DPlayerStats;
import br.com.projects.persistence.entities.Championship;
import br.com.projects.persistence.entities.Player;
import br.com.projects.persistence.entities.PlayerStats;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerStatsDomainToEntityAdapter implements Convertable<PlayerStats, DPlayerStats> {

    @Override
    public PlayerStats toEntity(DPlayerStats domain) {
        return PlayerStats.builder()
                .id(domain.getId())
                .player(new Player(domain.getPlayer().getId()))
                .championship(new Championship(domain.getChampionship().getId()))
                .goals(domain.getGoals())
                .yellowCards(domain.getYellowCards())
                .redCards(domain.getRedCards())
                .build();
    }

    @Override
    public DPlayerStats toDomain(PlayerStats entity) {
        return DPlayerStats.builder()
                .id(entity.getId())
                .player(Optional.ofNullable(entity.getPlayer())
                        .map(player -> new DPlayer(player.getId()))
                        .orElse(null))
                .championship(Optional.ofNullable(entity.getChampionship())
                        .map(championship -> new DChampionship(championship.getId()))
                        .orElse(null))
                .goals(entity.getGoals())
                .yellowCards(entity.getYellowCards())
                .redCards(entity.getRedCards())
                .build();
    }
}
