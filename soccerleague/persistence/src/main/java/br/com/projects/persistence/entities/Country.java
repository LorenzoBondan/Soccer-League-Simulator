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
@Table(name = "tb_country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@OneToOne(mappedBy = "country")
	private CountryAttachment countryAttachment;

	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<League> leagues = new ArrayList<>();

	public Country(Integer id) {
		this.id = id;
	}
}
