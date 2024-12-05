package br.com.projects.persistence.publico.player;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.enums.DPositionEnum;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.persistence.entities.Player;
import br.com.projects.persistence.entities.PlayerAttachment;
import br.com.projects.persistence.entities.Team;
import br.com.projects.persistence.entities.enums.PositionEnum;
import br.com.projects.persistence.publico.playerattachment.PlayerAttachmentDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerDomainToEntityAdapter implements Convertable<Player, DPlayer> {

    private final PlayerAttachmentDomainToEntityAdapter playerAttachmentDomainToEntityAdapter;

    public PlayerDomainToEntityAdapter(PlayerAttachmentDomainToEntityAdapter playerAttachmentDomainToEntityAdapter) {
        this.playerAttachmentDomainToEntityAdapter = playerAttachmentDomainToEntityAdapter;
    }

    @Override
    public Player toEntity(DPlayer domain) {
        return Player.builder()
                .id(domain.getId())
                .name(domain.getName())
                .nickname(domain.getNickname())
                .birthDate(domain.getBirthDate())
                .position(PositionEnum.valueOf(domain.getPosition().name()))
                .team(new Team(domain.getTeam().getId()))
                .playerAttachment(Optional.ofNullable(domain.getPlayerAttachment())
                        .map(playerAttachment -> new PlayerAttachment(playerAttachment.getId()))
                        .orElse(null))
                .build();
    }

    @Override
    public DPlayer toDomain(Player entity) {
        return DPlayer.builder()
                .id(entity.getId())
                .name(entity.getName())
                .nickname(entity.getNickname())
                .birthDate(entity.getBirthDate())
                .position(Optional.ofNullable(entity.getPosition())
                        .map(positionEnum -> DPositionEnum.valueOf(positionEnum.name()))
                        .orElse(null))
                .playerAttachment(Optional.ofNullable(entity.getPlayerAttachment())
                        .map(playerAttachmentDomainToEntityAdapter::toDomain)
                        .orElse(null))
                .build();
    }
}
