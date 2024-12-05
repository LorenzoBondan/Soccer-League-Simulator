package br.com.projects.persistence.publico.trophy;

import br.com.projects.domain.metadata.QueryService;
import br.com.projects.persistence.entities.Trophy;
import org.springframework.data.jpa.repository.JpaRepository;

@QueryService
public interface TrophyRepository extends JpaRepository<Trophy, Integer> {

}
