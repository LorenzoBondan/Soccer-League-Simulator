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
@Table(name = "tb_stadium_attachment")
public class StadiumAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "stadium_id")
	private Stadium stadium;

	@ManyToOne
	@JoinColumn(name = "attachment_id")
	private Attachment attachment;

	public StadiumAttachment(Integer id) {
		this.id = id;
	}
}
