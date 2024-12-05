package br.com.projects.persistence.publico.leagueattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.LeagueAttachment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface LeagueAttachmentQueryRepository extends PagingAndSortingRepository<LeagueAttachment, Integer>, JpaSpecificationExecutor<LeagueAttachment> {

    Collection<LeagueAttachment> findByLeague_IdAndAttachment_Id(Integer leagueId, Integer attachmentId);
}
