package br.com.projects.persistence.publico.position;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.position.DPosition;
import br.com.projects.persistence.entities.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionDomainToEntityAdapter implements Convertable<Position, DPosition> {

    @Override
    public Position toEntity(DPosition domain) {
        return Position.builder()
                .id(domain.getId())
                .name(domain.getName())
                .acronym(domain.getAcronym())
                .fieldZone(domain.getFieldZone())
                .build();
    }

    @Override
    public DPosition toDomain(Position entity) {
        return DPosition.builder()
                .id(entity.getId())
                .name(entity.getName())
                .acronym(entity.getAcronym())
                .fieldZone(entity.getFieldZone())
                .build();
    }
}
