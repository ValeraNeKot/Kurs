package main.Models.Entities;

import java.sql.Time;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

public class Schedule implements Serializable {
	@Expose
    private int IdSchedule;
    private List<Specialist> Specialists;
	@Expose
    private String days;
	@Expose
    private Time BeginTime;
	@Expose
    private Time EndTime;
	
	public Schedule() {};

	public Schedule(int idSchedule, List<Specialist> specialists, String days, Time beginTime, Time endTime) {
		IdSchedule = idSchedule;
		Specialists = specialists;
		this.days = days;
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
