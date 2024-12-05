package br.com.projects.domain.business.publico.trophyattachment;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.trophy.DTrophy;
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
public class DTrophyAttachment implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DTrophy trophy;
    private DAttachment attachment;

    public DTrophyAttachment(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Trophy", new ObjetoNaoNuloValidator()), this.trophy)
                .add(new NamedValidator<>("Attachment", new ObjetoNaoNuloValidator()), this.attachment)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
