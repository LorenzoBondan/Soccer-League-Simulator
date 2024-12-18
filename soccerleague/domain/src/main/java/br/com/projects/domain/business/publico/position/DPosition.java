package br.com.projects.domain.business.publico.position;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DPosition implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private String acronym;
    private Integer fieldZone;

    public DPosition(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Name", new ObjetoNaoNuloValidator()), this.name)
                .add(new NamedValidator<>("Name", new NaoBrancoValidator()), this.name)
                .add(new NamedValidator<>("Name", new CaracteresEspeciaisValidator()), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMinimoValidator(3)), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMaximoValidator(30)), this.name)
                .add(new NamedValidator<>("Acronym", new ObjetoNaoNuloValidator()), this.acronym)
                .add(new NamedValidator<>("Acronym", new NaoBrancoValidator()), this.acronym)
                .add(new NamedValidator<>("Acronym", new CaracteresEspeciaisValidator()), this.acronym)
                .add(new NamedValidator<>("Acronym", new TamanhoMinimoValidator(2)), this.acronym)
                .add(new NamedValidator<>("Acronym", new TamanhoMaximoValidator(3)), this.acronym)
                .add(new NamedValidator<>("Field Zone", new ObjetoNaoNuloValidator()), this.fieldZone)
                .add(new NamedValidator<>("Field Zone", new NumeroMaiorOuIgualAZeroValidator()), this.fieldZone)
                .validate();
    }

    @Override
    public String getDescricao() {
        return name;
    }
}
