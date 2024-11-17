package condorcet.Models.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vacancy")
public class Vacancy implements Serializable{
	@Id
	@OneToOne(mappedBy = "IdPost")
	@Column(name="post_id")
	private Post post;
	@Column(name="number")
	private int number;
	
	public Vacancy() {};
	
	public Vacancy(Post post, int number) {
		super();
		this.post = post;
		this.number = number;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	
	
}
