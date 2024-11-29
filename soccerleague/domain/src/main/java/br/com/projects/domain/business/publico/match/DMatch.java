package br.com.projects.domain.business.publico.match;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.exceptions.ValidationException;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.NumeroMaiorOuIgualAZeroValidator;
import br.com.projects.domain.validation.impl.ObjetoNaoNuloValidator;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DMatch implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    /*private DTeam homeTeam;
    private DTeam awayTeam;
    private DMatchDay matchDay;
    private DStadium stadium;*/
    private LocalDateTime date;
    private Integer attendees;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;

    //private List<DMatchEvents> matchEvents = new ArrayList<>();

    public DMatch(Integer id){
        this.id = id;
    }

    public void validar() throws ValidationException {
        new ValidationBuilder()
                /*.add(new NamedValidator<>("Home team", new ObjetoNaoNuloValidator()), this.homeTeam)
                .add(new NamedValidator<>("Away team", new ObjetoNaoNuloValidator()), this.awayTeam)
                .add(new NamedValidator<>("Stadium", new ObjetoNaoNuloValidator()), this.stadium)
                .add(new NamedValidator<>("Match Day", new ObjetoNaoNuloValidator()), this.matchDay)*/
                .add(new NamedValidator<>("Date", new ObjetoNaoNuloValidator()), this.date)
                .add(new NamedValidator<>("Atendees", new ObjetoNaoNuloValidator()), this.attendees)
                .add(new NamedValidator<>("Atendees", new NumeroMaiorOuIgualAZeroValidator()), this.attendees)
                .add(new NamedValidator<>("Home team goals", new ObjetoNaoNuloValidator()), this.homeTeamGoals)
                .add(new NamedValidator<>("Home team goals", new NumeroMaiorOuIgualAZeroValidator()), this.homeTeamGoals)
                .add(new NamedValidator<>("Away team goals", new ObjetoNaoNuloValidator()), this.awayTeamGoals)
                .add(new NamedValidator<>("Away team goals", new NumeroMaiorOuIgualAZeroValidator()), this.awayTeamGoals)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }

    /*private void calculateMatchResults() {
        int homeTeamGoals = 0;
        int awayTeamGoals = 0;
        for(DMatchEvent matchEvent : matchEvents) {
            if(matchEvent.getType().equals(DMatchEventType.GOAL)){
                if(matchEvent.getPlayer1().getTeam().equals(homeTeam)){
                    homeTeamGoals++;
                } else {
                    awayTeamGoals++;
                }
            }
        }
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }*/
}
