package br.com.projects.persistence.publico.playerstats;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Integer> {

}
