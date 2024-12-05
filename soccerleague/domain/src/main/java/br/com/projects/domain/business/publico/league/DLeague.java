package br.com.projects.domain.business.publico.league;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.country.DCountry;
import br.com.projects.domain.business.publico.leagueattachment.DLeagueAttachment;
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
public class DLeague implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private DCountry country;
    private DLeagueAttachment leagueAttachment;

    private List<DChampionship> championships = new ArrayList<>();

    public DLeague(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Name", new ObjetoNaoNuloValidator()), this.name)
                .add(new NamedValidator<>("Name", new NaoBrancoValidator()), this.name)
                .add(new NamedValidator<>("Name", new CaracteresEspeciaisValidator()), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMinimoValidator(3)), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMaximoValidator(50)), this.name)
                .add(new NamedValidator<>("Country", new ObjetoNaoNuloValidator()), this.country)
                .validate();
    }

    @Override
    public String getDescricao() {
        return name;
    }
}
