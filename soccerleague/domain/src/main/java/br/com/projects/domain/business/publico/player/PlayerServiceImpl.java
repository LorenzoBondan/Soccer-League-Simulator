package br.com.projects.domain.business.publico.player;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.player.api.PlayerService;
import br.com.projects.domain.business.publico.player.spi.CrudPlayer;
import br.com.projects.domain.exceptions.RegistroDuplicadoException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class PlayerServiceImpl implements PlayerService {

    private final CrudPlayer crudPlayer;

    public PlayerServiceImpl(CrudPlayer crudPlayer) {
        this.crudPlayer = crudPlayer;
    }

    @Override
    public DPlayer find(Integer id) {
        return crudPlayer.find(id);
    }

    @Override
    public Paged<DPlayer> find(PageableRequest request) {
        return crudPlayer.findAll(request);
    }

    @Override
    public DPlayer insert(DPlayer domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudPlayer.insert(domain);
    }

    @Override
    public DPlayer update(DPlayer domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudPlayer.update(domain);
    }

    @Override
    public void delete(Integer id) {
        crudPlayer.delete(id);
    }

    private void validateDuplicatedResource(DPlayer domain){
        if(crudPlayer.findByName(domain.getName())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new RegistroDuplicadoException("Verify the field name.");
        }
    }
}
