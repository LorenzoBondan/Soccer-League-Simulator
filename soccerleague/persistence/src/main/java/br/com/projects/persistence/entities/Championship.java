package br.com.projects.persistence.entities;

import lombok.*;

import jakarta.persistence.*;
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

	@ManyToOne
	@JoinColumn(name = "league_id")
	private League league;

	@ManyToOne
	@JoinColumn(name = "season_id")
	private Season season;

	@OneToMany(mappedBy = "championship")
	private List<Placing> placings = new ArrayList<>();

	@OneToMany(mappedBy = "championship")
	private List<Team> teams = new ArrayList<>();

	@OneToMany(mappedBy = "championship")
	private List<MatchDay> matchDays = new ArrayList<>();

	public Championship(Integer id) {
		this.id = id;
	}
}
