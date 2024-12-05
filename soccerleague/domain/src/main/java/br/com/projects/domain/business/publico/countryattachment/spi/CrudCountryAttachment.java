package br.com.projects.domain.business.publico.countryattachment.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.countryattachment.DCountryAttachment;

import java.util.Collection;

public interface CrudCountryAttachment extends SimpleCrud<DCountryAttachment, Integer> {

    Collection<? extends DCountryAttachment> findByCountryAndAttachment (Integer coutryId, Integer attachmentId);
}
