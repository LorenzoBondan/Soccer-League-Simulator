package br.com.projects.domain.business.publico.playerattachment.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.playerattachment.DPlayerAttachment;

import java.util.Collection;

public interface CrudPlayerAttachment extends SimpleCrud<DPlayerAttachment, Integer> {

    Collection<? extends DPlayerAttachment> findByPlayerAndAttachment (Integer playerId, Integer attachmentId);
}
