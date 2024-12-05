package br.com.projects.domain.business.publico.user;

import br.com.projects.domain.business.publico.role.DRole;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DUser {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private String password;
    private String email;

    private List<DRole> roles = new ArrayList<>();

    public DUser(Integer id){
        this.id = id;
    }

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Name", new ObjetoNaoNuloValidator()), this.name)
                .add(new NamedValidator<>("Name", new NaoBrancoValidator()), this.name)
                .add(new NamedValidator<>("Name", new CaracteresEspeciaisValidator()), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMinimoValidator(3)), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMaximoValidator(50)), this.name)
                .add(new NamedValidator<>("Password", new ObjetoNaoNuloValidator()), this.password)
                .add(new NamedValidator<>("Password", new NaoBrancoValidator()), this.password)
                .add(new NamedValidator<>("Password", new TamanhoMinimoValidator(5)), this.password)
                .add(new NamedValidator<>("Password", new TamanhoMaximoValidator(50)), this.password)
                .add(new NamedValidator<>("Email", new ObjetoNaoNuloValidator()), this.email)
                .add(new NamedValidator<>("Email", new EmailValidator()), this.email)
                .add(new NamedValidator<>("Roles", new ObjetoNaoNuloValidator()), this.roles)
                .validate();
    }
}
