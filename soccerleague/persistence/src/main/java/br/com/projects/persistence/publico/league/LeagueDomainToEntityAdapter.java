package br.com.projects.persistence.publico.league;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.country.DCountry;
import br.com.projects.domain.business.publico.league.DLeague;
import br.com.projects.persistence.entities.Country;
import br.com.projects.persistence.entities.League;
import br.com.projects.persistence.entities.LeagueAttachment;
import br.com.projects.persistence.publico.championship.ChampionshipDomainToEntityAdapter;
import br.com.projects.persistence.publico.leagueattachment.LeagueAttachmentDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LeagueDomainToEntityAdapter implements Convertable<League, DLeague> {

    private final LeagueAttachmentDomainToEntityAdapter leagueAttachmentDomainToEntityAdapter;
    private final ChampionshipDomainToEntityAdapter championshipDomainToEntityAdapter;

    public LeagueDomainToEntityAdapter(LeagueAttachmentDomainToEntityAdapter leagueAttachmentDomainToEntityAdapter, ChampionshipDomainToEntityAdapter championshipDomainToEntityAdapter) {
        this.leagueAttachmentDomainToEntityAdapter = leagueAttachmentDomainToEntityAdapter;
        this.championshipDomainToEntityAdapter = championshipDomainToEntityAdapter;
    }

    @Override
    public League toEntity(DLeague domain) {
        return League.builder()
                .id(domain.getId())
                .name(domain.getName())
                .country(new Country(domain.getCountry().getId()))
                .leagueAttachment(new LeagueAttachment(domain.getLeagueAttachment().getId()))
                .build();
    }

    @Override
    public DLeague toDomain(League entity) {

        List<DChampionship> championships = Optional.ofNullable(entity.getChampionships())
                .map(lista -> lista.stream()
                        .map(championshipDomainToEntityAdapter::toDomain)
                        .toList())
                .orElse(List.of());

        return DLeague.builder()
                .id(entity.getId())
                .name(entity.getName())
                .country(Optional.ofNullable(entity.getCountry())
                        .map(country -> new DCountry(country.getId()))
                        .orElse(null))
                .leagueAttachment(Optional.ofNullable(entity.getLeagueAttachment())
                        .map(leagueAttachmentDomainToEntityAdapter::toDomain)
                        .orElse(null))

                .championships(championships)

                .build();
    }
}
