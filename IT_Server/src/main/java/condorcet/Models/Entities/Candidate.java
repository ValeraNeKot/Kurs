package condorcet.Models.Entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="candidate")
public class Candidate implements Serializable{
	@Id
	@OneToOne(mappedBy = "Id")
	@Column(name = "id")
    private PersonData Id;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private Vacancy vacancy;
	@Column(name = "skills")
	private String Skills;
	@Column(name = "education")
	private String Education;
	@Column(name = "past_jobs")
	private String PastJobs;
	
	public Candidate() {};
	
	public Candidate(PersonData id, Vacancy vacancy, String skills, String education, String pastJobs) {
		super();
		Id = id;
		this.vacancy = vacancy;
		Skills = skills;
		Education = education;
		PastJobs = pastJobs;
	}

	public PersonData getId() {
		return Id;
	}

	public void setId(PersonData id) {
		Id = id;
	}

	public Vacancy getVacancy() {
		return vacancy;
	}

	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}

	public String getSkills() {
		return Skills;
	}
	public void setSkills(String skills) {
		Skills = skills;
	}
	public String getEducation() {
		return Education;
	}
	public void setEducation(String education) {
		Education = education;
	}
	public String getPastJobs() {
		return PastJobs;
	}
	public void setPastJobs(String pastJobs) {
		PastJobs = pastJobs;
	}
	
}
