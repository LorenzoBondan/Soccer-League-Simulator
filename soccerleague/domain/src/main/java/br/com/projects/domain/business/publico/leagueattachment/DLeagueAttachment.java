package br.com.projects.domain.business.publico.leagueattachment;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.league.DLeague;
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
public class DLeagueAttachment implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DLeague league;
    private DAttachment attachment;

    public DLeagueAttachment(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("League", new ObjetoNaoNuloValidator()), this.league)
                .add(new NamedValidator<>("Attachment", new ObjetoNaoNuloValidator()), this.attachment)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
