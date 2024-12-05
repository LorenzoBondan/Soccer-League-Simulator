package br.com.projects.persistence.publico.countryattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.CountryAttachment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface CountryAttachmentQueryRepository extends PagingAndSortingRepository<CountryAttachment, Integer>, JpaSpecificationExecutor<CountryAttachment> {

    Collection<CountryAttachment> findByCountry_IdAndAttachment_Id(Integer countryId, Integer attachmentId);
}
