package br.com.projects.persistence.publico.trophyattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.TrophyAttachment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface TrophyAttachmentQueryRepository extends PagingAndSortingRepository<TrophyAttachment, Integer>, JpaSpecificationExecutor<TrophyAttachment> {

    Collection<TrophyAttachment> findByTrophy_IdAndAttachment_Id(Integer trophyId, Integer attachmentId);
}
