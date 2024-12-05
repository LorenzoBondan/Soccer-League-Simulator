package br.com.projects.domain.business.publico.championship;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.championshipteam.DChampionshipTeam;
import br.com.projects.domain.business.publico.league.DLeague;
import br.com.projects.domain.business.publico.matchday.DMatchDay;
import br.com.projects.domain.business.publico.placing.DPlacing;
import br.com.projects.domain.business.publico.season.DSeason;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
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
public class DChampionship implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DLeague league;
    private DSeason season;

    private List<DPlacing> placings = new ArrayList<>();
    private List<DChampionshipTeam> championshipTeams = new ArrayList<>();
    private List<DMatchDay> matchDays = new ArrayList<>();

    public DChampionship(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("League", new ObjetoNaoNuloValidator()), this.league)
                .add(new NamedValidator<>("Season", new ObjetoNaoNuloValidator()), this.season)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
