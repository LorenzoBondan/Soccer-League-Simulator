package br.com.projects.domain.business.publico.matchday;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.match.DMatch;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.NumeroMaiorQueZeroValidator;
import br.com.projects.domain.validation.impl.ObjetoNaoNuloValidator;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DMatchDay implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DChampionship championship;
    private Integer number;

    private List<DMatch> matches = new ArrayList<>();

    public DMatchDay(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Championship", new ObjetoNaoNuloValidator()), this.championship)
                .add(new NamedValidator<>("Number", new ObjetoNaoNuloValidator()), this.number)
                .add(new NamedValidator<>("Number", new NumeroMaiorQueZeroValidator()), this.number)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
