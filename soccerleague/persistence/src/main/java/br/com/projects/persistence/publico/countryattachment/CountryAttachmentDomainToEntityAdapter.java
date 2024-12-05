package br.com.projects.persistence.publico.countryattachment;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.country.DCountry;
import br.com.projects.domain.business.publico.countryattachment.DCountryAttachment;
import br.com.projects.persistence.entities.Attachment;
import br.com.projects.persistence.entities.Country;
import br.com.projects.persistence.entities.CountryAttachment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CountryAttachmentDomainToEntityAdapter implements Convertable<CountryAttachment, DCountryAttachment> {

    @Override
    public CountryAttachment toEntity(DCountryAttachment domain) {
        return CountryAttachment.builder()
                .id(domain.getId())
                .country(new Country(domain.getCountry().getId()))
                .attachment(new Attachment(domain.getAttachment().getId()))
                .build();
    }

    @Override
    public DCountryAttachment toDomain(CountryAttachment entity) {
        return DCountryAttachment.builder()
                .id(entity.getId())
                .country(Optional.ofNullable(entity.getCountry())
                        .map(country -> new DCountry(country.getId()))
                        .orElse(null))
                .attachment(Optional.ofNullable(entity.getAttachment())
                        .map(attachment -> new DAttachment(attachment.getId()))
                        .orElse(null))
                .build();
    }
}
