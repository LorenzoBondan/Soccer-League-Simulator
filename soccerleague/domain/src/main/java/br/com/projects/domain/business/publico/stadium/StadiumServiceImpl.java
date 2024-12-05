package br.com.projects.domain.business.publico.stadium;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.stadium.api.StadiumService;
import br.com.projects.domain.business.publico.stadium.spi.CrudStadium;
import br.com.projects.domain.exceptions.RegistroDuplicadoException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class StadiumServiceImpl implements StadiumService {

    private final CrudStadium crudStadium;

    public StadiumServiceImpl(CrudStadium crudStadium) {
        this.crudStadium = crudStadium;
    }

    @Override
    public DStadium find(Integer id) {
        return crudStadium.find(id);
    }

    @Override
    public Paged<DStadium> find(PageableRequest request) {
        return crudStadium.findAll(request);
    }

    @Override
    public DStadium insert(DStadium domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudStadium.insert(domain);
    }

    @Override
    public DStadium update(DStadium domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudStadium.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudStadium.delete(id);
    }

    private void validateDuplicatedResource(DStadium domain){
        if(crudStadium.findByName(domain.getName())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new RegistroDuplicadoException("Verify the field name.");
        }
    }
}
