package br.com.projects.persistence.publico.playerattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.PlayerAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface PlayerAttachmentRepository extends JpaRepository<PlayerAttachment, Integer> {

}
