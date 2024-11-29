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
@Table(name = "tb_trophy_attachment")
public class TrophyAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trophy_id")
	private Trophy trophy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attachment_id")
	private Attachment attachment;

	public TrophyAttachment(Integer id) {
		this.id = id;
	}
}
