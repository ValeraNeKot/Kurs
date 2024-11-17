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
	@OneToOne(mappedBy = "IdPosition")
	@Column(name="position_id")
	private int IdPosition;
	@Column(name="number")
	private int number;
	
	public Vacancy() {};
	public Vacancy(int idPosition, int number) {
		IdPosition = idPosition;
		this.number = number;
	}
	public int getIdPosition() {
		return IdPosition;
	}
	public void setIdPosition(int idPosition) {
		IdPosition = idPosition;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
