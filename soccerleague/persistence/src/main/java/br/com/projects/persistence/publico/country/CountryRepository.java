package br.com.projects.persistence.publico.country;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
