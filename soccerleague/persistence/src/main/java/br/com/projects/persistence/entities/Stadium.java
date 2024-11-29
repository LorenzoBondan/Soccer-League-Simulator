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
@Table(name = "tb_stadium")
public class Stadium {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer capacity;
	private Double latitude;
	private Double longitude;

	@OneToOne(mappedBy = "stadium")
	private StadiumAttachment stadiumAttachment;

	@OneToMany(mappedBy = "stadium")
	private List<Team> teams = new ArrayList<>();

	public Stadium(Integer id) {
		this.id = id;
	}
}
