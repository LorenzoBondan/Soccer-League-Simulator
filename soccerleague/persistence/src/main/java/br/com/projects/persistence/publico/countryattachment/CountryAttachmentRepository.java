package br.com.projects.persistence.publico.countryattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.CountryAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface CountryAttachmentRepository extends JpaRepository<CountryAttachment, Integer> {

}
