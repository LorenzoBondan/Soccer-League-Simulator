package br.com.projects.domain.validation.impl;

import br.com.projects.domain.validation.ValidationResult;
import br.com.projects.domain.validation.Validator;

public class NaoBrancoValidator implements Validator<String> {

    @Override
    public ValidationResult validate(String s) {
        ValidationResult validationResult = new ValidationResult(true);
        if (s != null && s.trim().isEmpty()) {
            validationResult = new ValidationResult(false, "Não pode ser vazia ou conter apenas espaços.");
        }
        return validationResult;
    }
}
