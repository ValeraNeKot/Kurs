package condorcet.Models.Entities;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "specialist")
public class Specialist implements Serializable {
	@Id
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id",referencedColumnName = "person_id")
	@Expose
    private PersonData personData;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "specialist_schedule",  // Таблица соединения
        joinColumns = @JoinColumn(name = "person_id"),  // Внешний ключ для специалиста
        inverseJoinColumns = @JoinColumn(name = "schedule_id")  // Внешний ключ для расписания
    )
    @Expose
    private List<Schedule> Schedules;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id") // Связь с User
    private User user;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	@Expose
	private Department department;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
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
	public Specialist( List<Schedule> schedules, User user, Department department, Post position) {
		super();
		Schedules = schedules;
		this.user = user;
		this.department = department;
		this.position = position;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Schedules, department, personData, position, user);
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
