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
@Table(name = "tb_match_day")
public class MatchDay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "championship_id")
	private Championship championship;

	private Integer number;

	@OneToMany(mappedBy = "matchDay", fetch = FetchType.LAZY)
	private List<Match> matches = new ArrayList<>();

	public MatchDay(Integer id) {
		this.id = id;
	}
}
