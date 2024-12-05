package br.com.projects.domain.business.publico.teamattachment;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.attachment.DAttachment;
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
public class DTeamAttachment implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DTeam team;
    private DAttachment attachment;

    public DTeamAttachment(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Team", new ObjetoNaoNuloValidator()), this.team)
                .add(new NamedValidator<>("Attachment", new ObjetoNaoNuloValidator()), this.attachment)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
