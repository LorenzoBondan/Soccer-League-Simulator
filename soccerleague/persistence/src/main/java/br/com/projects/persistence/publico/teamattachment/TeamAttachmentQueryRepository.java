package br.com.projects.persistence.publico.teamattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.TeamAttachment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface TeamAttachmentQueryRepository extends PagingAndSortingRepository<TeamAttachment, Integer>, JpaSpecificationExecutor<TeamAttachment> {

    Collection<TeamAttachment> findByTeam_IdAndAttachment_Id(Integer teamId, Integer attachmentId);
}
