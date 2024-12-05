package br.com.projects.domain.business.publico.country;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.countryattachment.DCountryAttachment;
import br.com.projects.domain.business.publico.league.DLeague;
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
public class DCountry implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private DCountryAttachment countryAttachment;

    private List<DLeague> leagues = new ArrayList<>();

    public DCountry(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Name", new ObjetoNaoNuloValidator()), this.name)
                .add(new NamedValidator<>("Name", new NaoBrancoValidator()), this.name)
                .add(new NamedValidator<>("Name", new CaracteresEspeciaisValidator()), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMinimoValidator(3)), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMaximoValidator(50)), this.name)
                .validate();
    }

    @Override
    public String getDescricao() {
        return name;
    }
}
