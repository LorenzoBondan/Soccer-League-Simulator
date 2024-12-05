package br.com.projects.persistence.publico.playerattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.PlayerAttachment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface PlayerAttachmentQueryRepository extends PagingAndSortingRepository<PlayerAttachment, Integer>, JpaSpecificationExecutor<PlayerAttachment> {

    Collection<PlayerAttachment> findByPlayer_IdAndAttachment_Id(Integer playerId, Integer attachmentId);
}
