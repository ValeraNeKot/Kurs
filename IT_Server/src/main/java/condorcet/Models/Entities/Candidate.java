package condorcet.Models.Entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "candidate")
public class Candidate implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id") // Убедитесь, что у PersonData есть соответствующий идентификатор
    @Expose
    private PersonData id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "vacancy_id", referencedColumnName = "vacancy_id") // Укажите правильный внешний ключ
    @Expose
    private Vacancy vacancy;
    @Column(name = "skills")
    @Expose
    private String skills;
    @Column(name = "education")
    @Expose
    private String education;
    @Column(name = "past_jobs")
    @Expose
    private String pastJobs;

	
	public Candidate() {}


	public Candidate(PersonData id, Vacancy vacancy, String skills, String education, String pastJobs) {
		super();
		this.id = id;
		this.vacancy = vacancy;
		this.skills = skills;
		this.education = education;
		this.pastJobs = pastJobs;
	}


	@Override
	public int hashCode() {
		return Objects.hash(education, id, pastJobs, skills, vacancy);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		return Objects.equals(education, other.education) && Objects.equals(id, other.id)
				&& Objects.equals(pastJobs, other.pastJobs) && Objects.equals(skills, other.skills)
				&& Objects.equals(vacancy, other.vacancy);
	}


	public PersonData getId() {
		return id;
	}


	public void setId(PersonData id) {
		this.id = id;
	}


	public Vacancy getVacancy() {
		return vacancy;
	}


	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}


	public String getSkills() {
		return skills;
	}


	public void setSkills(String skills) {
		this.skills = skills;
	}


	public String getEducation() {
		return education;
	}


	public void setEducation(String education) {
		this.education = education;
	}


	public String getPastJobs() {
		return pastJobs;
	}


	public void setPastJobs(String pastJobs) {
		this.pastJobs = pastJobs;
	};
	
	
	
}
