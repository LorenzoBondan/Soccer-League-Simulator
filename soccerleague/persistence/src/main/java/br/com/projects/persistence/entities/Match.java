package br.com.projects.persistence.entities;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_match")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_Team_id")
	private Team homeTeam;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_Team_id")
	private Team awayTeam;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_day_id")
	private MatchDay matchDay;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stadium_id")
	private Stadium stadium;

	private LocalDateTime date;
	private Integer attendees;

	private Integer homeTeamGoals;
	private Integer awayTeamGoals;

	@OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
	private List<MatchEvent> matchEvents = new ArrayList<>();

	public Match(Integer id) {
		this.id = id;
	}
}
