package br.com.projects.domain.business.publico.countryattachment;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.attachment.DAttachment;
import br.com.projects.domain.business.publico.country.DCountry;
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
public class DCountryAttachment implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DCountry country;
    private DAttachment attachment;

    public DCountryAttachment(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Country", new ObjetoNaoNuloValidator()), this.country)
                .add(new NamedValidator<>("Attachment", new ObjetoNaoNuloValidator()), this.attachment)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
