package br.com.projects.persistence.publico.player;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
