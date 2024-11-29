package br.com.projects.domain.business.publico.attachment;

import br.com.projects.domain.Descritivel;
import br.com.projects.domain.business.publico.binary.DBinary;
import br.com.projects.domain.exceptions.ValidationException;
import br.com.projects.domain.validation.NamedValidator;
import br.com.projects.domain.validation.ValidationBuilder;
import br.com.projects.domain.validation.impl.NaoBrancoValidator;
import br.com.projects.domain.validation.impl.ObjetoNaoNuloValidator;
import br.com.projects.domain.validation.impl.TamanhoMaximoValidator;
import br.com.projects.domain.validation.impl.TamanhoMinimoValidator;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DAttachment implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private DBinary binary;
    private String name;
    private String mimeType;
    private String url;
    private String checksum;

    //private List<DMatchEvents> matchEvents = new ArrayList<>();

    public DAttachment(Integer id){
        this.id = id;
    }

    public void validar() throws ValidationException {
        new ValidationBuilder()

                .add(new NamedValidator<>("Binary", new ObjetoNaoNuloValidator()), this.binary)
                .add(new NamedValidator<>("Name", new NaoBrancoValidator()), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMinimoValidator(3)), this.name)
                .add(new NamedValidator<>("Name", new TamanhoMaximoValidator(50)), this.name)
                .add(new NamedValidator<>("Mime Type", new NaoBrancoValidator()), this.mimeType)
                .add(new NamedValidator<>("Mime Type", new TamanhoMaximoValidator(20)), this.mimeType)
                .add(new NamedValidator<>("Checksum", new NaoBrancoValidator()), this.checksum)
                .validate();
    }

    @Override
    public String getDescricao() {
        return name;
    }
}
