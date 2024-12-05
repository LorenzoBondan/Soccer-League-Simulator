package br.com.projects.domain.business.publico.matchevent;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.enums.DMatchEventType;
import br.com.projects.domain.business.publico.match.DMatch;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DMatchEvent implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DMatch match;
    private DMatchEventType type;
    private String description;
    private DPlayer player1;
    private DPlayer player2;
    private Integer minuteMatchEvent;

    public DMatchEvent(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Match", new ObjetoNaoNuloValidator()), this.match)
                .add(new NamedValidator<>("Type", new ObjetoNaoNuloValidator()), this.type)
                .add(new NamedValidator<>("Description", new NaoBrancoValidator()), this.description)
                .add(new NamedValidator<>("Description", new CaracteresEspeciaisValidator()), this.description)
                .add(new NamedValidator<>("Description", new TamanhoMinimoValidator(3)), this.description)
                .add(new NamedValidator<>("Description", new TamanhoMaximoValidator(50)), this.description)
                .add(new NamedValidator<>("Player 1", new ObjetoNaoNuloValidator()), this.player1)
                .add(new NamedValidator<>("Minute", new ObjetoNaoNuloValidator()), this.minuteMatchEvent)
                .add(new NamedValidator<>("Minute", new NumeroMaiorQueZeroValidator()), this.minuteMatchEvent)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
