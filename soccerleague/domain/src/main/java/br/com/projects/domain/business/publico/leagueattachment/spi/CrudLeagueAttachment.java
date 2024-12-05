package br.com.projects.domain.business.publico.leagueattachment.spi;

import br.com.projects.domain.SimpleCrud;
import br.com.projects.domain.business.publico.leagueattachment.DLeagueAttachment;

import java.util.Collection;

public interface CrudLeagueAttachment extends SimpleCrud<DLeagueAttachment, Integer> {

    Collection<? extends DLeagueAttachment> findByLeagueAndAttachment (Integer leagueId, Integer attachmentId);
}
