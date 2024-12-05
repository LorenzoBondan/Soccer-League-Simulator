package br.com.projects.persistence.publico.trophyattachment;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.trophy.DTrophy;
import br.com.projects.domain.business.publico.trophyattachment.DTrophyAttachment;
import br.com.projects.persistence.entities.Attachment;
import br.com.projects.persistence.entities.Trophy;
import br.com.projects.persistence.entities.TrophyAttachment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrophyAttachmentDomainToEntityAdapter implements Convertable<TrophyAttachment, DTrophyAttachment> {

    @Override
    public TrophyAttachment toEntity(DTrophyAttachment domain) {
        return TrophyAttachment.builder()
                .id(domain.getId())
                .trophy(new Trophy(domain.getTrophy().getId()))
                .attachment(new Attachment(domain.getAttachment().getId()))
                .build();
    }

    @Override
    public DTrophyAttachment toDomain(TrophyAttachment entity) {
        return DTrophyAttachment.builder()
                .id(entity.getId())
                .trophy(Optional.ofNullable(entity.getTrophy())
                        .map(Trophy -> new DTrophy(Trophy.getId()))
                        .orElse(null))
                .attachment(Optional.ofNullable(entity.getAttachment())
                        .map(attachment -> new DAttachment(attachment.getId()))
                        .orElse(null))
                .build();
    }
}
