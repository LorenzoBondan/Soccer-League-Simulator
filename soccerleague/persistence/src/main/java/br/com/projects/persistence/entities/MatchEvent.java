package br.com.projects.persistence.entities;

import br.com.projects.persistence.entities.enums.MatchEventType;
import lombok.*;

import jakarta.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_match_event")
public class MatchEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "match_id")
	private Match match;

	@Enumerated(EnumType.STRING)
	private MatchEventType type;

	private String description;

	@ManyToOne
	@JoinColumn(name = "player1_id")
	private Player player1;

	@ManyToOne
	@JoinColumn(name = "player2_id")
	private Player player2;

	private Integer minuteMatchEvent;

	public MatchEvent(Integer id) {
		this.id = id;
	}
}
