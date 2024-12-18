package br.com.projects.domain.business.publico.playerstats;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.NumeroMaiorOuIgualAZeroValidator;
import br.com.projects.domain.validation.impl.ObjetoNaoNuloValidator;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DPlayerStats implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DPlayer player;
    private DChampionship championship;
    private Integer goals;
    private Integer yellowCards;
    private Integer redCards;

    public DPlayerStats(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Player", new ObjetoNaoNuloValidator()), this.player)
                .add(new NamedValidator<>("Championship", new ObjetoNaoNuloValidator()), this.championship)
                .add(new NamedValidator<>("Goals", new ObjetoNaoNuloValidator()), this.goals)
                .add(new NamedValidator<>("Goals", new NumeroMaiorOuIgualAZeroValidator()), this.goals)
                .add(new NamedValidator<>("Yellow Cards", new ObjetoNaoNuloValidator()), this.yellowCards)
                .add(new NamedValidator<>("Yellow Cards", new NumeroMaiorOuIgualAZeroValidator()), this.yellowCards)
                .add(new NamedValidator<>("Red Cards", new ObjetoNaoNuloValidator()), this.redCards)
                .add(new NamedValidator<>("Red Cards", new NumeroMaiorOuIgualAZeroValidator()), this.redCards)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
