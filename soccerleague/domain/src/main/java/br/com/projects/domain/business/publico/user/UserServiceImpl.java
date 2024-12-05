package br.com.projects.domain.business.publico.user;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.business.publico.user.api.UserService;
import br.com.projects.domain.business.publico.user.spi.CrudUser;
import br.com.projects.domain.exceptions.RegistroDuplicadoException;
import br.com.projects.domain.metadata.DomainService;

import java.util.Optional;

@DomainService
public class UserServiceImpl implements UserService {

    private final CrudUser crudUser;

    public UserServiceImpl(CrudUser crudUser) {
        this.crudUser = crudUser;
    }

    @Override
    public DUser find(Integer id) {
        return crudUser.find(id);
    }

    @Override
    public DUser find(String email) {
        return crudUser.findByEmail(email).stream().findFirst().orElse(null);
    }

    @Override
    public Paged<DUser> find(PageableRequest request) {
        return crudUser.findAll(request);
    }

    @Override
    public DUser incluir(DUser domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudUser.insert(domain);
    }

    @Override
    public DUser update(DUser domain) {
        domain.validate();
        validateDuplicatedResource(domain);
        return crudUser.update(domain);
    }

    @Override
    public void updatePassword(String newPassword, String oldPassword) {
        crudUser.updatePassword(newPassword, oldPassword);
    }

    @Override
    public void delete(Integer id) {
        crudUser.delete(id);
    }

    private void validateDuplicatedResource(DUser domain){
        if(crudUser.findByEmail(domain.getEmail())
                .stream()
                .anyMatch(t -> !t.getId().equals(Optional.ofNullable(domain.getId()).orElse(-1)))){
            throw new RegistroDuplicadoException("Verifique o campo email.");
        }
    }
}
