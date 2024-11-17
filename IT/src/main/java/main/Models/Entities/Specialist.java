package main.Models.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="specialist")
public class Specialist {
	@Id
	@OneToOne
	@Column(name ="id")
    private PersonData Id;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Schedule> Schedules;
	@OneToOne(mappedBy ="IdAccount")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Department department;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "position_id")
	private Post position;
	@Column(name = "hire_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hireDate;
	
	public Specialist() {};

	public Specialist(PersonData id, List<Schedule> schedules, User user, Department department, Post position,
			Date hireDate) {
		super();
		Id = id;
		Schedules = schedules;
		this.user = user;
		this.department = department;
		this.position = position;
		this.hireDate = hireDate;
	}



	public PersonData getId() {
		return Id;
	}

	public void setId(PersonData id) {
		Id = id;
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


	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}



	public List<Schedule> getSchedules() {
		return Schedules;
	}



	public void setSchedules(List<Schedule> schedules) {
		Schedules = schedules;
	}
	
}
