package br.com.projects.domain.business.publico.trophyattachment.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.trophyattachment.DTrophyAttachment;

import java.util.Collection;

public interface CrudTrophyAttachment extends SimpleCrud<DTrophyAttachment, Integer> {

    Collection<? extends DTrophyAttachment> findByTrophyAndAttachment (Integer trophyId, Integer attachmentId);
}
