package br.com.projects.persistence.publico.playerattachment;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.domain.business.publico.playerattachment.DPlayerAttachment;
import br.com.projects.persistence.entities.Attachment;
import br.com.projects.persistence.entities.Player;
import br.com.projects.persistence.entities.PlayerAttachment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerAttachmentDomainToEntityAdapter implements Convertable<PlayerAttachment, DPlayerAttachment> {

    @Override
    public PlayerAttachment toEntity(DPlayerAttachment domain) {
        return PlayerAttachment.builder()
                .id(domain.getId())
                .player(new Player(domain.getPlayer().getId()))
                .attachment(new Attachment(domain.getAttachment().getId()))
                .build();
    }

    @Override
    public DPlayerAttachment toDomain(PlayerAttachment entity) {
        return DPlayerAttachment.builder()
                .id(entity.getId())
                .player(Optional.ofNullable(entity.getPlayer())
                        .map(player -> new DPlayer(player.getId()))
                        .orElse(null))
                .attachment(Optional.ofNullable(entity.getAttachment())
                        .map(attachment -> new DAttachment(attachment.getId()))
                        .orElse(null))
                .build();
    }
}
