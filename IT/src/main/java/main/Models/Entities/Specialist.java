package main.Models.Entities;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Specialist implements Serializable {
	@Expose
    private PersonData personData;
    @Expose
    private List<Schedule> Schedules;
    private User user;
	@Expose
	private Department department;
	@Expose
	private Post position;

	
	public Specialist() {};

	public Specialist(PersonData id, List<Schedule> schedules, User user, Department department, Post position) {
		super();
		personData = id;
		Schedules = schedules;
		this.user = user;
		this.department = department;
		this.position = position;
	}
	
	


	 public javafx.beans.property.IntegerProperty idProperty() {
	        return new javafx.beans.property.SimpleIntegerProperty(personData.getId());
	    }

	 public javafx.beans.property.StringProperty postProperty() {
	        return new javafx.beans.property.SimpleStringProperty(position.getNamePost());
	    }
	 
	 public javafx.beans.property.StringProperty departmentProperty() {
	        return new javafx.beans.property.SimpleStringProperty(department.getNameDepartment());
	    }
	 public javafx.beans.property.StringProperty roleProperty() {
	        return new javafx.beans.property.SimpleStringProperty(user.getRole().name());
	    }
	@Override
	public int hashCode() {
		return Objects.hash(Schedules, department,  personData, position, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specialist other = (Specialist) obj;
		return Objects.equals(Schedules, other.Schedules) && Objects.equals(department, other.department)
				 && Objects.equals(personData, other.personData)
				&& Objects.equals(position, other.position) && Objects.equals(user, other.user);
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Post getPosition() {
		return position;
	}


	public void setPosition(Post position) {
		this.position = position;
	}



	public List<Schedule> getSchedules() {
		return Schedules;
	}



	public void setSchedules(List<Schedule> schedules) {
		Schedules = schedules;
	}

	public PersonData getPersonData() {
		return personData;
	}

	public void setPersonData(PersonData personData) {
		this.personData = personData;
	}
	
}
