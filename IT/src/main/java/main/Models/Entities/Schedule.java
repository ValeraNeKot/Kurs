package main.Models.Entities;

import java.sql.Time;
import java.time.LocalTime;
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
    private String BeginTime;
	@Expose
    private String EndTime;
	
	public Schedule() {};

	public Schedule(int idSchedule, List<Specialist> specialists, String days,String beginTime, String endTime) {
		IdSchedule = idSchedule;
		Specialists = specialists;
		this.days = days;
		BeginTime = beginTime;
		EndTime = endTime;
	}
	
	public Schedule(List<Specialist> specialists, String days, String beginTime, String endTime) {
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
