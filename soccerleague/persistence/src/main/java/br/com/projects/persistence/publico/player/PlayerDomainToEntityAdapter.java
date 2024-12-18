package br.com.projects.persistence.publico.player;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.persistence.entities.Player;
import br.com.projects.persistence.entities.PlayerAttachment;
import br.com.projects.persistence.entities.Position;
import br.com.projects.persistence.entities.Team;
import br.com.projects.persistence.publico.playerattachment.PlayerAttachmentDomainToEntityAdapter;
import br.com.projects.persistence.publico.position.PositionDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerDomainToEntityAdapter implements Convertable<Player, DPlayer> {

    private final PlayerAttachmentDomainToEntityAdapter playerAttachmentDomainToEntityAdapter;
    private final PositionDomainToEntityAdapter positionDomainToEntityAdapter;

    public PlayerDomainToEntityAdapter(PlayerAttachmentDomainToEntityAdapter playerAttachmentDomainToEntityAdapter, PositionDomainToEntityAdapter positionDomainToEntityAdapter) {
        this.playerAttachmentDomainToEntityAdapter = playerAttachmentDomainToEntityAdapter;
        this.positionDomainToEntityAdapter = positionDomainToEntityAdapter;
    }

    @Override
    public Player toEntity(DPlayer domain) {
        return Player.builder()
                .id(domain.getId())
                .name(domain.getName())
                .nickname(domain.getNickname())
                .birthDate(domain.getBirthDate())
                .team(new Team(domain.getTeam().getId()))
                .playerAttachment(Optional.ofNullable(domain.getPlayerAttachment())
                        .map(playerAttachment -> new PlayerAttachment(playerAttachment.getId()))
                        .orElse(null))

                .positions(domain.getPositions().stream().map(position -> new Position(position.getId())).collect(Collectors.toSet()))

                .build();
    }

    @Override
    public DPlayer toDomain(Player entity) {
        return DPlayer.builder()
                .id(entity.getId())
                .name(entity.getName())
                .nickname(entity.getNickname())
                .birthDate(entity.getBirthDate())
                .playerAttachment(Optional.ofNullable(entity.getPlayerAttachment())
                        .map(playerAttachmentDomainToEntityAdapter::toDomain)
                        .orElse(null))

                .positions(entity.getPositions().stream().map(positionDomainToEntityAdapter::toDomain).collect(Collectors.toList()))

                .build();
    }
}
