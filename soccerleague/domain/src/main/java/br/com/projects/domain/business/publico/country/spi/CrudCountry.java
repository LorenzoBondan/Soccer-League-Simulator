package br.com.projects.domain.business.publico.country.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.country.DCountry;

import java.util.Collection;

public interface CrudCountry extends SimpleCrud<DCountry, Integer> {

    Collection<? extends DCountry> findByName (String name);
}
