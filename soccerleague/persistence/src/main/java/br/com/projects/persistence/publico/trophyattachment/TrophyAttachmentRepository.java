package br.com.projects.persistence.publico.trophyattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.TrophyAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface TrophyAttachmentRepository extends JpaRepository<TrophyAttachment, Integer> {

}
