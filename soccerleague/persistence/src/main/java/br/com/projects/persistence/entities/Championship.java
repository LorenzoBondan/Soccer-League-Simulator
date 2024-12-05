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
@Table(name = "tb_championship")
public class Championship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "league_id")
	private League league;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "season_id")
	private Season season;

	@OneToMany(mappedBy = "championship", fetch = FetchType.LAZY)
	private List<Placing> placings = new ArrayList<>();

	@OneToMany(mappedBy = "championship", fetch = FetchType.LAZY)
	private List<ChampionshipTeam> championshipsTeam = new ArrayList<>();

	@OneToMany(mappedBy = "championship", fetch = FetchType.LAZY)
	private List<MatchDay> matchDays = new ArrayList<>();

	@OneToMany(mappedBy = "championship", fetch = FetchType.LAZY)
	private List<PlayerStats> playerStats = new ArrayList<>();

	public Championship(Integer id) {
		this.id = id;
	}
}
