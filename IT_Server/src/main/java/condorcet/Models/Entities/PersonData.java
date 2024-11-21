package condorcet.Models.Entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="person_data")
public class PersonData implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="person_id")
    private int Id;
	@Column(name="name")
	private String Name;
	@Column(name="age")
    private int Age;
	@Column(name="mail")
    private String Mail;
	@Column(name="phone_number")
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
