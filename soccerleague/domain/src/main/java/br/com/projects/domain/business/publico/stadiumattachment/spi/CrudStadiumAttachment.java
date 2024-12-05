package br.com.projects.domain.business.publico.stadiumattachment.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.stadiumattachment.DStadiumAttachment;

import java.util.Collection;

public interface CrudStadiumAttachment extends SimpleCrud<DStadiumAttachment, Integer> {

    Collection<? extends DStadiumAttachment> findByStadiumAndAttachment (Integer stadiumId, Integer attachmentId);
}
