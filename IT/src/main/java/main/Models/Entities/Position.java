package main.Models.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="position")
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdPosition;
	@Column(name= "position_name")
	private String NamePosition;
	@Column(name= "responsibility")
	private String Responsibility;
	
	public Position() {};
	public Position(int idPosition, String namePosition, String responsibility) {
		super();
		IdPosition = idPosition;
		NamePosition = namePosition;
		Responsibility = responsibility;
	}
	public int getIdPosition() {
		return IdPosition;
	}
	public void setIdPosition(int idPosition) {
		IdPosition = idPosition;
	}
	public String getNamePosition() {
		return NamePosition;
	}
	public void setNamePosition(String namePosition) {
		NamePosition = namePosition;
	}
	public String getResponsibility() {
		return Responsibility;
	}
	public void setResponsibility(String responsibility) {
		Responsibility = responsibility;
	}
	
}
