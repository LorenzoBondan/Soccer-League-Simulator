package br.com.projects.persistence.publico.country;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.country.DCountry;
import br.com.projects.domain.business.publico.league.DLeague;
import br.com.projects.persistence.entities.Country;
import br.com.projects.persistence.entities.CountryAttachment;
import br.com.projects.persistence.publico.countryattachment.CountryAttachmentDomainToEntityAdapter;
import br.com.projects.persistence.publico.league.LeagueDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CountryDomainToEntityAdapter implements Convertable<Country, DCountry> {

    private final CountryAttachmentDomainToEntityAdapter countryAttachmentDomainToEntityAdapter;
    private final LeagueDomainToEntityAdapter leagueDomainToEntityAdapter;

    public CountryDomainToEntityAdapter(CountryAttachmentDomainToEntityAdapter countryAttachmentDomainToEntityAdapter, LeagueDomainToEntityAdapter leagueDomainToEntityAdapter) {
        this.countryAttachmentDomainToEntityAdapter = countryAttachmentDomainToEntityAdapter;
        this.leagueDomainToEntityAdapter = leagueDomainToEntityAdapter;
    }

    @Override
    public Country toEntity(DCountry domain) {
        return Country.builder()
                .id(domain.getId())
                .name(domain.getName())
                .countryAttachment(Optional.ofNullable(domain.getCountryAttachment())
                        .map(countryAttachment -> new CountryAttachment(countryAttachment.getId()))
                        .orElse(null))
                .build();
    }

    @Override
    public DCountry toDomain(Country entity) {

        List<DLeague> leagues = Optional.ofNullable(entity.getLeagues())
                .map(lista -> lista.stream()
                        .map(leagueDomainToEntityAdapter::toDomain)
                        .toList())
                .orElse(List.of());

        return DCountry.builder()
                .id(entity.getId())
                .name(entity.getName())
                .countryAttachment(Optional.ofNullable(entity.getCountryAttachment())
                        .map(countryAttachmentDomainToEntityAdapter::toDomain)
                        .orElse(null))

                .leagues(leagues)

                .build();
    }
}
