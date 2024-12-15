package main.Models.Entities;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

public class PersonData implements Serializable{
	@Expose
    private int Id;
	@Expose
	private String Name;
	@Expose
    private int Age;
	@Expose
    private String Mail;
	@Expose
    private String PhoneNumber;
	
    public PersonData(){
    }

    public PersonData(int id, String name, int age, String mail, String phoneNumber) {
		super();
		Id = id;
		Name = name;
		Age = age;
		Mail = mail;
		PhoneNumber = phoneNumber;
	}
    
    public PersonData(String name, int age, String mail, String phoneNumber) {
		super();
		Name = name;
		Age = age;
		Mail = mail;
		PhoneNumber = phoneNumber;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	
}
