package br.com.projects.domain.business.publico.trophyteam;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.team.DTeam;
import br.com.projects.domain.business.publico.trophy.DTrophy;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.NumeroMaiorQueZeroValidator;
import br.com.projects.domain.validation.impl.ObjetoNaoNuloValidator;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DTrophyTeam implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DTrophy trophy;
    private DTeam team;
    private Integer quantity;

    public DTrophyTeam(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Trophy", new ObjetoNaoNuloValidator()), this.trophy)
                .add(new NamedValidator<>("Team", new ObjetoNaoNuloValidator()), this.team)
                .add(new NamedValidator<>("Quantity", new ObjetoNaoNuloValidator()), this.quantity)
                .add(new NamedValidator<>("Quantity", new NumeroMaiorQueZeroValidator()), this.quantity)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
