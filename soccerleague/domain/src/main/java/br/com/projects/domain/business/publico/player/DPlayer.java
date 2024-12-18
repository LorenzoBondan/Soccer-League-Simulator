package br.com.projects.domain.business.publico.player;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.playerattachment.DPlayerAttachment;
import br.com.projects.domain.business.publico.position.DPosition;
import br.com.projects.domain.business.publico.team.DTeam;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DPlayer implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private String nickname;
    private LocalDate birthDate;
    private DTeam team;
    private DPlayerAttachment playerAttachment;

    private List<DPosition> positions = new ArrayList<>();

    public DPlayer(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Name", new ObjetoNaoNuloValidator()), this.name)
                .add(new NamedValidator<>("Name", new NaoBrancoValidator()), this.name)
                .add(new NamedValidator<>("Name", new CaracteresEspeciaisValidator()), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMinimoValidator(3)), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMaximoValidator(100)), this.name)
                .add(new NamedValidator<>("Nickname", new ObjetoNaoNuloValidator()), this.name)
                .add(new NamedValidator<>("Nickname", new NaoBrancoValidator()), this.name)
                .add(new NamedValidator<>("Nickname", new CaracteresEspeciaisValidator()), this.name)
                .add(new NamedValidator<>("Nickname", new TamanhoMinimoValidator(3)), this.name)
                .add(new NamedValidator<>("Nickname", new TamanhoMaximoValidator(50)), this.name)
                .add(new NamedValidator<>("Birth Date", new ObjetoNaoNuloValidator()), this.birthDate)
                .add(new NamedValidator<>("Team", new ObjetoNaoNuloValidator()), this.team)
                .validate();
    }

    @Override
    public String getDescricao() {
        return name;
    }
}
