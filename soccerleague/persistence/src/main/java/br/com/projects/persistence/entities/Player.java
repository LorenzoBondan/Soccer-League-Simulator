package br.com.projects.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_player_position",
			joinColumns = @JoinColumn(name = "player_id"),
			inverseJoinColumns = @JoinColumn(name = "position_id")
	)
	private Set<Position> positions = new HashSet<>();
	
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
