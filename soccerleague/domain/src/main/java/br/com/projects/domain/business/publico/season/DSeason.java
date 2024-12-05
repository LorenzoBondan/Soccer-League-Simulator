package br.com.projects.domain.business.publico.season;

import br.com.projects.domain.Descritivel;
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
public class DSeason implements Descritivel {

    @EqualsAndHashCode.Include
    private Integer id;
    private Integer seasonYear;

    public DSeason(Integer id){
        this.id = id;
    };

    public void validate(){
        new ValidationBuilder()
                .add(new NamedValidator<>("Year", new ObjetoNaoNuloValidator()), this.seasonYear)
                .add(new NamedValidator<>("Year", new NumeroMaiorQueZeroValidator()), this.seasonYear)
                .validate();
    }

    @Override
    public String getDescricao() {
        return null;
    }
}
