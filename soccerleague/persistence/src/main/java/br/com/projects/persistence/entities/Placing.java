package br.com.projects.persistence.entities;

import lombok.*;

import jakarta.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_placing")
public class Placing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "championship_id")
	private Championship championship;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	private Integer points;
	private Integer victories;
	private Integer draws;
	private Integer defeats;
	private Integer goalsScored;
	private Integer goalsConceded;
	private Integer goalDifference;

	public Placing(Integer id) {
		this.id = id;
	}
}
