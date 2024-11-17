package condorcet.Models.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="specialist")
public class Specialist {
	@Id
	@OneToOne(mappedBy = "Id")
	@Column(name ="id")
    private int Id;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Schedule> Schedules;
	@OneToOne(mappedBy ="IdAccount")
	private int IdAccount;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Department department;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "position_id")
	private Position position;
	@Column(name = "hire_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hireDate;
	
	public Specialist() {};
	

	public Specialist(int id, List<Schedule> schedules, int idAccount, Department department, Position position,
			Date hireDate) {
		super();
		Id = id;
		Schedules = schedules;
		IdAccount = idAccount;
		this.department = department;
		this.position = position;
		this.hireDate = hireDate;
	}


	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getIdAccount() {
		return IdAccount;
	}

	public void setIdAccount(int idAccount) {
		IdAccount = idAccount;
	}

	

	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Position getPosition() {
		return position;
	}


	public void setPosition(Position position) {
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
