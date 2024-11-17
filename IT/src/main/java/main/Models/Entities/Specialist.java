package main.Models.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="specialist")
public class Specialist {
	@Id
	@OneToOne(mappedBy = "person_data")
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private int Id;
	@OneToOne()
	private int IdAccount;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private int IdDepartment;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private int IdPosition;
	@Column(name = "hire_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hireDate;
	
	public Specialist() {};
	
	public Specialist(int id, int idAccount, int iddepartment, int idposition, Date hireDate) {
		super();
		Id = id;
		IdAccount = idAccount;
		this.IdDepartment = iddepartment;
		this.IdPosition = idposition;
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

	

	public int getIdDepartment() {
		return IdDepartment;
	}

	public void setIdDepartment(int idDepartment) {
		IdDepartment = idDepartment;
	}

	public int getIdPosition() {
		return IdPosition;
	}

	public void setIdPosition(int idPosition) {
		IdPosition = idPosition;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
}
