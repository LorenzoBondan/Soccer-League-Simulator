package br.com.projects.domain.validation.impl;

import br.com.projects.domain.validation.ValidationResult;
import br.com.projects.domain.validation.Validator;

public class ObjetoNaoNuloValidator implements Validator<Object> {
    @Override
    public ValidationResult validate(Object o) {
        ValidationResult result = new ValidationResult(true);
        if(o == null){
            result = new ValidationResult(false, "Não pode ser nulo");
        }
        return result;
    }
}
