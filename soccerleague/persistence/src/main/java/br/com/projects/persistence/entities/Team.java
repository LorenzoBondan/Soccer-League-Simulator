package br.com.projects.persistence.entities;

import lombok.*;

import jakarta.persistence.*;
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
@Table(name = "tb_team")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer members;

	@ManyToOne
	@JoinColumn(name = "stadium_id")
	private Stadium stadium;

	@ManyToOne
	@JoinColumn(name = "championship_id")
	private Championship championship;

	@OneToOne(mappedBy = "team")
	private TeamAttachment teamAttachment;
	
	@OneToMany(mappedBy = "team")
	private List<Player> players = new ArrayList<>();
	
	@OneToMany(mappedBy = "homeTeam")
    private List<Match> matchesHome = new ArrayList<>();

	@OneToMany(mappedBy = "awayTeam")
	private List<Match> matchesAway = new ArrayList<>();

	@OneToMany(mappedBy = "team")
	private List<Placing> placings = new ArrayList<>();

	@OneToMany(mappedBy = "team")
	private List<TrophyTeam> trophyTeams = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_team_rival",
				joinColumns = @JoinColumn(name = "team_id"), 
				inverseJoinColumns = @JoinColumn(name = "rival_id")
			)
	private Set<Team> rivals = new HashSet<>();

	public Team(Integer id) {
		this.id = id;
	}
}
