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
@Table(name = "tb_trophy_team")
public class TrophyTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trophy_id")
	private Trophy trophy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	private Integer quantity;

	public TrophyTeam(Integer id) {
		this.id = id;
	}
}
