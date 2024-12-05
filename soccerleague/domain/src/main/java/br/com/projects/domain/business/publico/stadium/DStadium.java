package br.com.projects.domain.business.publico.stadium;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.stadiumattachment.DStadiumAttachment;
import br.com.projects.domain.business.publico.team.DTeam;
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
public class DStadium implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private Integer capacity;
    private Double latitude;
    private Double longitude;
    private DStadiumAttachment stadiumAttachment;

    private List<DTeam> teams = new ArrayList<>();

    public DStadium(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Name", new ObjetoNaoNuloValidator()), this.name)
                .add(new NamedValidator<>("Name", new NaoBrancoValidator()), this.name)
                .add(new NamedValidator<>("Name", new CaracteresEspeciaisValidator()), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMinimoValidator(3)), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMaximoValidator(50)), this.name)
                .add(new NamedValidator<>("Capacity", new ObjetoNaoNuloValidator()), this.capacity)
                .add(new NamedValidator<>("Capacity", new NumeroMaiorQueZeroValidator()), this.capacity)
                .validate();
    }

    @Override
    public String getDescricao() {
        return name;
    }
}
