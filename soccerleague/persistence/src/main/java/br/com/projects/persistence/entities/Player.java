package br.com.projects.persistence.entities;

import br.com.projects.persistence.entities.enums.PositionEnum;
import jakarta.persistence.Entity;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String nickname;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDate birthDate;
	@Enumerated(EnumType.STRING)
	private PositionEnum position;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToOne(mappedBy = "player")
	private PlayerAttachment playerAttachment;

	@OneToMany(mappedBy = "player1")
	private List<MatchEvent> matchEvents = new ArrayList<>();

	@OneToMany(mappedBy = "player2")
	private List<MatchEvent> matchEventsPlayer2 = new ArrayList<>();

	public Player(Integer id){
		this.id = id;
	}
}
