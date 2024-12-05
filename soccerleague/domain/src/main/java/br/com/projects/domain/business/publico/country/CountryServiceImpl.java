package br.com.projects.domain.business.publico.country;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.country.api.CountryService;
import br.com.projects.domain.business.publico.country.spi.CrudCountry;
import br.com.projects.domain.exceptions.RegistroDuplicadoException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class CountryServiceImpl implements CountryService {

    private final CrudCountry crudCountry;

    public CountryServiceImpl(CrudCountry crudCountry) {
        this.crudCountry = crudCountry;
    }

    @Override
    public DCountry find(Integer id) {
        return crudCountry.find(id);
    }

    @Override
    public Paged<DCountry> find(PageableRequest request) {
        return crudCountry.findAll(request);
    }

    @Override
    public DCountry insert(DCountry domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudCountry.insert(domain);
    }

    @Override
    public DCountry update(DCountry domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudCountry.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudCountry.delete(id);
    }

    private void validateDuplicatedResource(DCountry domain){
        if(crudCountry.findByName(domain.getName())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new RegistroDuplicadoException("Verify the field name.");
        }
    }
}
