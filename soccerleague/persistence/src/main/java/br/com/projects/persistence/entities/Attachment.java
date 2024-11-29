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
@Table(name = "tb_attachment")
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "binary_id")
	private Binary binary;

	private String name;
	private String mimeType;
	private String url;
	@Column(columnDefinition = "TEXT")
	private String checksum;

	@OneToMany(mappedBy = "attachment")
	private List<CountryAttachment> countryAttachments = new ArrayList<>();

	@OneToMany(mappedBy = "attachment")
	private List<LeagueAttachment> leagueAttachments = new ArrayList<>();

	@OneToMany(mappedBy = "attachment")
	private List<PlayerAttachment> playerAttachments = new ArrayList<>();

	@OneToMany(mappedBy = "attachment")
	private List<TeamAttachment> teamAttachments = new ArrayList<>();

	@OneToMany(mappedBy = "attachment")
	private List<StadiumAttachment> stadiumAttachments = new ArrayList<>();

	@OneToMany(mappedBy = "attachment")
	private List<TrophyAttachment> trophyAttachments = new ArrayList<>();

	public Attachment(Integer id) {
		this.id = id;
	}
}
