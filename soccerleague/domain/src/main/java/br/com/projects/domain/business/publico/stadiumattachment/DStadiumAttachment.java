package br.com.projects.domain.business.publico.stadiumattachment;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.stadium.DStadium;
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
public class DStadiumAttachment implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DStadium stadium;
    private DAttachment attachment;

    public DStadiumAttachment(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Stadium", new ObjetoNaoNuloValidator()), this.stadium)
                .add(new NamedValidator<>("Attachment", new ObjetoNaoNuloValidator()), this.attachment)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
