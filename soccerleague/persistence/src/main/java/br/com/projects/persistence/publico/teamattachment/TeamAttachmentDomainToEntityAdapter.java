package br.com.projects.persistence.publico.teamattachment;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.team.DTeam;
import br.com.projects.domain.business.publico.teamattachment.DTeamAttachment;
import br.com.projects.persistence.entities.Attachment;
import br.com.projects.persistence.entities.Team;
import br.com.projects.persistence.entities.TeamAttachment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeamAttachmentDomainToEntityAdapter implements Convertable<TeamAttachment, DTeamAttachment> {

    @Override
    public TeamAttachment toEntity(DTeamAttachment domain) {
        return TeamAttachment.builder()
                .id(domain.getId())
                .team(new Team(domain.getTeam().getId()))
                .attachment(new Attachment(domain.getAttachment().getId()))
                .build();
    }

    @Override
    public DTeamAttachment toDomain(TeamAttachment entity) {
        return DTeamAttachment.builder()
                .id(entity.getId())
                .team(Optional.ofNullable(entity.getTeam())
                        .map(Team -> new DTeam(Team.getId()))
                        .orElse(null))
                .attachment(Optional.ofNullable(entity.getAttachment())
                        .map(attachment -> new DAttachment(attachment.getId()))
                        .orElse(null))
                .build();
    }
}
