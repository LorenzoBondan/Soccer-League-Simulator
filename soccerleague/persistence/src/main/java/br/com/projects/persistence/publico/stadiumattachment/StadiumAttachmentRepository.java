package br.com.projects.persistence.publico.stadiumattachment;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.StadiumAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface StadiumAttachmentRepository extends JpaRepository<StadiumAttachment, Integer> {

}
