package br.com.projects.persistence.entities;

import br.com.projects.persistence.entities.enums.PositionEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String nickname;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDate birthDate;
	@Enumerated(EnumType.STRING)
	private PositionEnum position;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToOne(mappedBy = "player")
	private PlayerAttachment playerAttachment;

	@OneToMany(mappedBy = "player1", fetch = FetchType.LAZY)
	private List<MatchEvent> matchEvents = new ArrayList<>();

	@OneToMany(mappedBy = "player2", fetch = FetchType.LAZY)
	private List<MatchEvent> matchEventsPlayer2 = new ArrayList<>();

	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
	private List<PlayerStats> playerStats = new ArrayList<>();

	public Player(Integer id){
		this.id = id;
	}
}
