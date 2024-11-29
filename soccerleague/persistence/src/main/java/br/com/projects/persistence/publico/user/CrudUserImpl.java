package br.com.projects.persistence.publico.user;

import br.com.projects.domain.PageableRequest;
import br.com.projects.domain.Paged;
import br.com.projects.domain.PagedBuilder;
import br.com.projects.domain.business.publico.user.DUser;
import br.com.projects.domain.business.publico.user.spi.CrudUser;
import br.com.projects.domain.exceptions.DatabaseException;
import br.com.projects.domain.exceptions.ResourceNotFoundException;
import br.com.projects.persistence.entities.User;
import br.com.projects.persistence.util.CustomUserUtil;
import br.com.projects.persistence.util.PageRequestUtils;
import br.com.projects.persistence.util.SpecificationHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Component
public class CrudUserImpl implements CrudUser {

    private final UserRepository repository;
    private final UserQueryRepository queryRepository;
    private final UserDomainToEntityAdapter adapter;
    private final PageRequestUtils pageRequestUtils;
    private final CustomUserUtil customUserUtil;

    public CrudUserImpl(UserRepository repository, UserQueryRepository queryRepository, UserDomainToEntityAdapter adapter, PageRequestUtils pageRequestUtils, CustomUserUtil customUserUtil) {
        this.repository = repository;
        this.queryRepository = queryRepository;
        this.adapter = adapter;
        this.pageRequestUtils = pageRequestUtils;
        this.customUserUtil = customUserUtil;
    }

    @Override
    @Transactional(readOnly = true)
    public Paged<DUser> buscarTodos(PageableRequest request) {
        SpecificationHelper<User> helper = new SpecificationHelper<>();
        Specification<User> specification = helper.buildSpecification(request.getColunas(), request.getOperacoes(), request.getValores());

        return Optional.of(queryRepository.findAll(specification, pageRequestUtils.toPage(request)))
                .map(r -> new PagedBuilder<DUser>()
                        .withContent(r.getContent().stream().map(adapter::toDomain).toList())
                        .withSortedBy(String.join(";", request.getSort()))
                        .withFirst(r.isFirst())
                        .withLast(r.isLast())
                        .withPage(r.getNumber())
                        .withSize(r.getSize())
                        .withTotalPages(r.getTotalPages())
                        .withNumberOfElements(Math.toIntExact(r.getTotalElements()))
                        .build())
                .orElse(null);
    }

    @Override
    public Collection<? extends DUser> findByEmail(String email) {
        return repository.findByEmail(email).stream().map(adapter::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DUser buscar(Integer id) {
        return adapter.toDomain(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Código não encontrado: " + id)));
    }

    @Override
    @Transactional
    public DUser inserir(DUser obj) {
        return adapter.toDomain(repository.save(adapter.toEntity(obj)));
    }

    @Override
    @Transactional
    public DUser atualizar(DUser obj) {
        if(!repository.existsById(obj.getId())){
            throw new ResourceNotFoundException("Código não encontrado: " + obj.getId());
        }
        User entity = adapter.toEntity(obj);
        return adapter.toDomain(repository.save(entity));
    }

    @Override
    @Transactional
    public void updatePassword(String newPassword, String oldPassword) {
        User me = repository.findByEmail(customUserUtil.getLoggedUsername()).stream().findFirst().get();
        repository.updatePassword(newPassword, Long.valueOf(me.getId()));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void remover(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
        try{
            repository.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
