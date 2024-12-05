package br.com.projects.persistence.publico.trophy;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.trophy.DTrophy;
import br.com.projects.persistence.entities.Trophy;
import br.com.projects.persistence.entities.TrophyAttachment;
import br.com.projects.persistence.publico.trophyattachment.TrophyAttachmentDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrophyDomainToEntityAdapter implements Convertable<Trophy, DTrophy> {

    private final TrophyAttachmentDomainToEntityAdapter trophyAttachmentDomainToEntityAdapter;

    public TrophyDomainToEntityAdapter(TrophyAttachmentDomainToEntityAdapter trophyAttachmentDomainToEntityAdapter) {
        this.trophyAttachmentDomainToEntityAdapter = trophyAttachmentDomainToEntityAdapter;
    }

    @Override
    public Trophy toEntity(DTrophy domain) {
        return Trophy.builder()
                .id(domain.getId())
                .name(domain.getName())
                .trophyAttachment(Optional.ofNullable(domain.getTrophyAttachment())
                        .map(TrophyAttachment -> new TrophyAttachment(TrophyAttachment.getId()))
                        .orElse(null))
                .build();
    }

    @Override
    public DTrophy toDomain(Trophy entity) {
        return DTrophy.builder()
                .id(entity.getId())
                .name(entity.getName())
                .trophyAttachment(Optional.ofNullable(entity.getTrophyAttachment())
                        .map(trophyAttachmentDomainToEntityAdapter::toDomain)
                        .orElse(null))
                .build();
    }
}
