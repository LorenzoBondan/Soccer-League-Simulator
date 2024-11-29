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
@Table(name = "tb_player_attachment")
public class PlayerAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "player_id")
	private Player player;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attachment_id")
	private Attachment attachment;

	public PlayerAttachment(Integer id) {
		this.id = id;
	}
}
