package condorcet.Models.Entities;

import java.sql.Time;
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
    @ManyToMany(mappedBy = "Schedules", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private List<Specialist> Specialists;
	@Column(name = "days", nullable = false)
	@Expose
    private String days;
	@Column(name = "begin_time", nullable = false)
    //@Temporal(TemporalType.TIME)
	@Expose
    private Time BeginTime;
	@Column(name = "end_time", nullable = false)
    //@Temporal(TemporalType.TIME)
	@Expose
    private Time EndTime;
	
	public Schedule() {};

	public Schedule(int idSchedule, List<Specialist> specialists, String date, Time beginTime, Time endTime) {
		IdSchedule = idSchedule;
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
