package br.com.projects.domain.business.publico.championshipteam;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.championship.DChampionship;
import br.com.projects.domain.business.publico.league.DLeague;
import br.com.projects.domain.business.publico.season.DSeason;
import br.com.projects.domain.business.publico.team.DTeam;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.ObjetoNaoNuloValidator;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DChampionshipTeam implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DChampionship championship;
    private DTeam team;

    public DChampionshipTeam(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Championship", new ObjetoNaoNuloValidator()), this.championship)
                .add(new NamedValidator<>("Team", new ObjetoNaoNuloValidator()), this.team)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
