package br.com.projects.persistence.publico.leagueattachment;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.league.DLeague;
import br.com.projects.domain.business.publico.leagueattachment.DLeagueAttachment;
import br.com.projects.persistence.entities.Attachment;
import br.com.projects.persistence.entities.League;
import br.com.projects.persistence.entities.LeagueAttachment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LeagueAttachmentDomainToEntityAdapter implements Convertable<LeagueAttachment, DLeagueAttachment> {

    @Override
    public LeagueAttachment toEntity(DLeagueAttachment domain) {
        return LeagueAttachment.builder()
                .id(domain.getId())
                .league(new League(domain.getLeague().getId()))
                .attachment(new Attachment(domain.getAttachment().getId()))
                .build();
    }

    @Override
    public DLeagueAttachment toDomain(LeagueAttachment entity) {
        return DLeagueAttachment.builder()
                .id(entity.getId())
                .league(Optional.ofNullable(entity.getLeague())
                        .map(League -> new DLeague(League.getId()))
                        .orElse(null))
                .attachment(Optional.ofNullable(entity.getAttachment())
                        .map(attachment -> new DAttachment(attachment.getId()))
                        .orElse(null))
                .build();
    }
}
