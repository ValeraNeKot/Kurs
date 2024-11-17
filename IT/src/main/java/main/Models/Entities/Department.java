package main.Models.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "department_id")
	private int IdDepartment;
	@Column(name= "department_name")
	private String NameDepartment;
	
	public Department() {};
	public Department(int idDepartment, String nameDepartment) {
		super();
		IdDepartment = idDepartment;
		NameDepartment = nameDepartment;
	}
	public int getIdDepartment() {
		return IdDepartment;
	}
	public void setIdDepartment(int idDepartment) {
		IdDepartment = idDepartment;
	}
	public String getNameDepartment() {
		return NameDepartment;
	}
	public void setNameDepartment(String nameDepartment) {
		NameDepartment = nameDepartment;
	}
	
}
