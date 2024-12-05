package br.com.projects.persistence.publico.stadiumattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.StadiumAttachment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface StadiumAttachmentQueryRepository extends PagingAndSortingRepository<StadiumAttachment, Integer>, JpaSpecificationExecutor<StadiumAttachment> {

    Collection<StadiumAttachment> findByStadium_IdAndAttachment_Id(Integer stadiumId, Integer attachmentId);
}
