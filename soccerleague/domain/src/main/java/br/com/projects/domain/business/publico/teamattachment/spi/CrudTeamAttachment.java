package br.com.projects.domain.business.publico.teamattachment.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.teamattachment.DTeamAttachment;

import java.util.Collection;

public interface CrudTeamAttachment extends SimpleCrud<DTeamAttachment, Integer> {

    Collection<? extends DTeamAttachment> findByTeamAndAttachment (Integer teamId, Integer attachmentId);
}
