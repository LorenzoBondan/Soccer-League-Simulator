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
@Table(name = "tb_league")
public class League {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@OneToOne(mappedBy = "league")
	private LeagueAttachment leagueAttachment;

	@OneToMany(mappedBy = "league")
	private List<Championship> championships = new ArrayList<>();

	public League(Integer id) {
		this.id = id;
	}
}
