package main.Models.Entities;

import java.sql.Time;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="schedule")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdSchedule;
	@ManyToMany(mappedBy = "specialist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private int Id;
	@Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
	@Column(name = "begin_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Time BeginTime;
	@Column(name = "end_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Time EndTime;
	
	public Schedule() {};
	public Schedule(int idSchedule, int id, Date date, Time beginTime, Time endTime) {
		super();
		IdSchedule = idSchedule;
		Id = id;
		this.date = date;
		BeginTime = beginTime;
		EndTime = endTime;
	}
	public int getIdSchedule() {
		return IdSchedule;
	}
	public void setIdSchedule(int idSchedule) {
		IdSchedule = idSchedule;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getBeginTime() {
		return BeginTime;
	}
	public void setBeginTime(Time beginTime) {
		BeginTime = beginTime;
	}
	public Time getEndTime() {
		return EndTime;
	}
	public void setEndTime(Time endTime) {
		EndTime = endTime;
	}
	
}
