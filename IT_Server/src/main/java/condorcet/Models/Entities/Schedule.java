package condorcet.Models.Entities;

import java.sql.Time;
import java.util.List;
import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name="schedule")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
    private int IdSchedule;
	@ManyToMany(mappedBy = "Schedules", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "specialist_shedule",
	joinColumns = {@JoinColumn(name = "schedule_id")},
	inverseJoinColumns = { @JoinColumn(name = "specialist_id") }
	)
    private List<Specialist> Specialists;
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

	public Schedule(int idSchedule, List<Specialist> specialists, Date date, Time beginTime, Time endTime) {
		IdSchedule = idSchedule;
		Specialists = specialists;
		this.date = date;
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
