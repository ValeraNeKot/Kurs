package main.Models.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

public class Department implements Serializable{
	@Expose
	private int IdDepartment;
	@Expose
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
	
	public javafx.beans.property.IntegerProperty idProperty() {
        return new javafx.beans.property.SimpleIntegerProperty(IdDepartment);
    }

    public javafx.beans.property.StringProperty postProperty() {
        return new javafx.beans.property.SimpleStringProperty(NameDepartment);
    }

}
