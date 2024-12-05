package br.com.projects.persistence.publico.leagueattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.LeagueAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface LeagueAttachmentRepository extends JpaRepository<LeagueAttachment, Integer> {

}
