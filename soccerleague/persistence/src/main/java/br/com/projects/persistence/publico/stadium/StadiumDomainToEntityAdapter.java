package br.com.projects.persistence.publico.stadium;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.stadium.DStadium;
import br.com.projects.domain.business.publico.team.DTeam;
import br.com.projects.persistence.entities.Stadium;
import br.com.projects.persistence.entities.StadiumAttachment;
import br.com.projects.persistence.publico.stadiumattachment.StadiumAttachmentDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StadiumDomainToEntityAdapter implements Convertable<Stadium, DStadium> {

    private final StadiumAttachmentDomainToEntityAdapter stadiumAttachmentDomainToEntityAdapter;

    public StadiumDomainToEntityAdapter(StadiumAttachmentDomainToEntityAdapter stadiumAttachmentDomainToEntityAdapter) {
        this.stadiumAttachmentDomainToEntityAdapter = stadiumAttachmentDomainToEntityAdapter;
    }

    @Override
    public Stadium toEntity(DStadium domain) {
        return Stadium.builder()
                .id(domain.getId())
                .name(domain.getName())
                .capacity(domain.getCapacity())
                .latitude(domain.getLatitude())
                .longitude(domain.getLongitude())
                .stadiumAttachment(Optional.ofNullable(domain.getStadiumAttachment())
                        .map(stadiumAttachment -> new StadiumAttachment(stadiumAttachment.getId()))
                        .orElse(null))
                .build();
    }

    @Override
    public DStadium toDomain(Stadium entity) {

        List<DTeam> teams = Optional.ofNullable(entity.getTeams())
                .map(lista -> lista.stream()
                        .map(team -> new DTeam(team.getId()))
                        .toList())
                .orElse(List.of());

        return DStadium.builder()
                .id(entity.getId())
                .name(entity.getName())
                .capacity(entity.getCapacity())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .stadiumAttachment(Optional.ofNullable(entity.getStadiumAttachment())
                        .map(stadiumAttachmentDomainToEntityAdapter::toDomain)
                        .orElse(null))

                .teams(teams)

                .build();
    }
}
