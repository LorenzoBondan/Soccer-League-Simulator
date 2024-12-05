package br.com.projects.domain.business.publico.placing;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.team.DTeam;
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
public class DPlacing implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DChampionship championship;
    private DTeam team;
    private Integer points;
    private Integer victories;
    private Integer draws;
    private Integer defeats;
    private Integer goalsScored;
    private Integer goalsConceded;
    private Integer goalDifference;

    public DPlacing(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Championship", new ObjetoNaoNuloValidator()), this.championship)
                .add(new NamedValidator<>("Team", new ObjetoNaoNuloValidator()), this.team)
                .add(new NamedValidator<>("Points", new ObjetoNaoNuloValidator()), this.points)
                .add(new NamedValidator<>("Points", new NumeroMaiorOuIgualAZeroValidator()), this.points)
                .add(new NamedValidator<>("Victories", new ObjetoNaoNuloValidator()), this.victories)
                .add(new NamedValidator<>("Victories", new NumeroMaiorOuIgualAZeroValidator()), this.victories)
                .add(new NamedValidator<>("Draws", new ObjetoNaoNuloValidator()), this.draws)
                .add(new NamedValidator<>("Draws", new NumeroMaiorOuIgualAZeroValidator()), this.draws)
                .add(new NamedValidator<>("Defeats", new ObjetoNaoNuloValidator()), this.defeats)
                .add(new NamedValidator<>("Defeats", new NumeroMaiorOuIgualAZeroValidator()), this.defeats)
                .add(new NamedValidator<>("Goals Scored", new ObjetoNaoNuloValidator()), this.goalsScored)
                .add(new NamedValidator<>("Goals Scored", new NumeroMaiorOuIgualAZeroValidator()), this.goalsScored)
                .add(new NamedValidator<>("Goals Conceded", new ObjetoNaoNuloValidator()), this.goalsConceded)
                .add(new NamedValidator<>("Goals Conceded", new NumeroMaiorOuIgualAZeroValidator()), this.goalsConceded)
                .add(new NamedValidator<>("Goal Difference", new ObjetoNaoNuloValidator()), this.goalDifference)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
