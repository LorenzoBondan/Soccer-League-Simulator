package br.com.projects.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_team")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer members;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stadium_id")
	private Stadium stadium;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "championship_id")
	private Championship championship;

	@OneToOne(mappedBy = "team")
	private TeamAttachment teamAttachment;
	
	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<Player> players = new ArrayList<>();
	
	@OneToMany(mappedBy = "homeTeam", fetch = FetchType.LAZY)
    private List<Match> matchesHome = new ArrayList<>();

	@OneToMany(mappedBy = "awayTeam", fetch = FetchType.LAZY)
	private List<Match> matchesAway = new ArrayList<>();

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<Placing> placings = new ArrayList<>();

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<TrophyTeam> trophyTeams = new ArrayList<>();

	public Team(Integer id) {
		this.id = id;
	}
}
