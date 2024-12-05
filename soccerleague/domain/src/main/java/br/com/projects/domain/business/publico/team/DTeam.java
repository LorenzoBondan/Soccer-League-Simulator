package br.com.projects.domain.business.publico.team;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.championshipteam.DChampionshipTeam;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.domain.business.publico.stadium.DStadium;
import br.com.projects.domain.business.publico.teamattachment.DTeamAttachment;
import br.com.projects.domain.business.publico.trophyteam.DTrophyTeam;
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
public class DTeam implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private Integer members;
    private DStadium stadium;
    private DTeamAttachment teamAttachment;

    private List<DChampionshipTeam> championshipsTeam = new ArrayList<>();
    private List<DPlayer> players = new ArrayList<>();
    private List<DTrophyTeam> trophiesTeam = new ArrayList<>();

    public DTeam(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Name", new ObjetoNaoNuloValidator()), this.name)
                .add(new NamedValidator<>("Name", new NaoBrancoValidator()), this.name)
                .add(new NamedValidator<>("Name", new CaracteresEspeciaisValidator()), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMinimoValidator(3)), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMaximoValidator(50)), this.name)
                .add(new NamedValidator<>("Members", new NumeroMaiorOuIgualAZeroValidator()), this.members)
                .validate();
    }

    @Override
    public String getDescricao() {
        return name;
    }
}
