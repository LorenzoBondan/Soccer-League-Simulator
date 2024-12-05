package br.com.projects.domain.business.publico.match;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.matchday.DMatchDay;
import br.com.projects.domain.business.publico.matchevent.DMatchEvent;
import br.com.projects.domain.business.publico.stadium.DStadium;
import br.com.projects.domain.business.publico.team.DTeam;
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
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DMatch implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DTeam homeTeam;
    private DTeam awayTeam;
    private DMatchDay matchDay;
    private DStadium stadium;
    private LocalDateTime date;
    private Integer attendees;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;

    private List<DMatchEvent> matchEvents = new ArrayList<>();

    public DMatch(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Home Team", new ObjetoNaoNuloValidator()), this.homeTeam)
                .add(new NamedValidator<>("Away Team", new ObjetoNaoNuloValidator()), this.awayTeam)
                .add(new NamedValidator<>("Match day", new ObjetoNaoNuloValidator()), this.matchDay)
                .add(new NamedValidator<>("Stadium", new ObjetoNaoNuloValidator()), this.stadium)
                .add(new NamedValidator<>("Date", new ObjetoNaoNuloValidator()), this.date)
                .add(new NamedValidator<>("Attendees", new NumeroMaiorOuIgualAZeroValidator()), this.attendees)
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
}
