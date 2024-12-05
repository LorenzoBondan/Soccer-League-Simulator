package br.com.projects.persistence.publico.teamattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.TeamAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface TeamAttachmentRepository extends JpaRepository<TeamAttachment, Integer> {

}
