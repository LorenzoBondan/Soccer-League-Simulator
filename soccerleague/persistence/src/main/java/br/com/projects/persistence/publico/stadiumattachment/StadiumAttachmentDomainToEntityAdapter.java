package br.com.projects.persistence.publico.stadiumattachment;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.stadium.DStadium;
import br.com.projects.domain.business.publico.stadiumattachment.DStadiumAttachment;
import br.com.projects.persistence.entities.Attachment;
import br.com.projects.persistence.entities.Stadium;
import br.com.projects.persistence.entities.StadiumAttachment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StadiumAttachmentDomainToEntityAdapter implements Convertable<StadiumAttachment, DStadiumAttachment> {

    @Override
    public StadiumAttachment toEntity(DStadiumAttachment domain) {
        return StadiumAttachment.builder()
                .id(domain.getId())
                .stadium(new Stadium(domain.getStadium().getId()))
                .attachment(new Attachment(domain.getAttachment().getId()))
                .build();
    }

    @Override
    public DStadiumAttachment toDomain(StadiumAttachment entity) {
        return DStadiumAttachment.builder()
                .id(entity.getId())
                .stadium(Optional.ofNullable(entity.getStadium())
                        .map(stadium -> new DStadium(stadium.getId()))
                        .orElse(null))
                .attachment(Optional.ofNullable(entity.getAttachment())
                        .map(attachment -> new DAttachment(attachment.getId()))
                        .orElse(null))
                .build();
    }
}
