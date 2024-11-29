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
@Table(name = "tb_league_attachment")
public class LeagueAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "league_id")
	private League league;

	@ManyToOne
	@JoinColumn(name = "attachment_id")
	private Attachment attachment;

	public LeagueAttachment(Integer id) {
		this.id = id;
	}
}
