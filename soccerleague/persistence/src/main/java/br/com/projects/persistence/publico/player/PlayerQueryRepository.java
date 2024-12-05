package br.com.projects.persistence.publico.player;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Player;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

@QueryService
public interface PlayerQueryRepository extends PagingAndSortingRepository<Player, Integer>, JpaSpecificationExecutor<Player> {

    Collection<Player> findByNameIgnoreCase(String name);
}
