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
@Table(name = "tb_trophy")
public class Trophy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@OneToOne(mappedBy = "trophy")
	private TrophyAttachment trophyAttachment;

	@OneToMany(mappedBy = "trophy")
	private List<TrophyTeam> trophyTeams = new ArrayList<>();

	public Trophy(Integer id) {
		this.id = id;
	}
}
