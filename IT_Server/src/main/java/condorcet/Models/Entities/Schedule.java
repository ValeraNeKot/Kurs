package condorcet.Models.Entities;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.google.gson.annotations.Expose;


@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    @Expose
    private int IdSchedule;
    @ManyToMany(mappedBy = "Schedules", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Specialist> Specialists;
	@Column(name = "days")
	@Expose
    private String days;
	@Column(name = "begin_time")
	@Expose
    private String BeginTime;
	@Column(name = "end_time")
	@Expose
    private String EndTime;
	
	public Schedule() {};

	public Schedule(int idSchedule, List<Specialist> specialists, String date, String beginTime, String endTime) {
		IdSchedule = idSchedule;
		Specialists = specialists;
		this.days = date;
		BeginTime = beginTime;
		EndTime = endTime;
	}
	public Schedule( List<Specialist> specialists, String date, String beginTime, String endTime) {
		Specialists = specialists;
		this.days = date;
		BeginTime = beginTime;
		EndTime = endTime;
	}

	public List<Specialist> getSpecialists() {
		return Specialists;
	}

	public void setSpecialists(List<Specialist> specialists) {
		Specialists = specialists;
	}

	public int getIdSchedule() {
		return IdSchedule;
	}
	public void setIdSchedule(int idSchedule) {
		IdSchedule = idSchedule;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getBeginTime() {
		return BeginTime;
	}
	public void setBeginTime(String beginTime) {
		BeginTime = beginTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	
}
