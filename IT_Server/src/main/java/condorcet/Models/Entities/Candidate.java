package condorcet.Models.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="candidate")
public class Candidate {
	@Id
	@OneToOne(mappedBy = "person_data")
    private int Id;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private int IdPosition;
	@Column(name = "skills")
	private String Skills;
	@Column(name = "education")
	private String Education;
	@Column(name = "past_jobs")
	private String PastJobs;
	
	public Candidate() {};
	public Candidate(int id, int idPosition, String skills, String education, String pastJobs) {
		super();
		Id = id;
		IdPosition = idPosition;
		Skills = skills;
		Education = education;
		PastJobs = pastJobs;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getIdPosition() {
		return IdPosition;
	}
	public void setIdPosition(int idPosition) {
		IdPosition = idPosition;
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
