package main.Models.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vacancy")
public class Vacancy {
	@Id
	@OneToOne(mappedBy = "position")
	private int IdPosition;
	@Column(name="number")
	private int number;
}
