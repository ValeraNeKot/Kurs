package condorcet.Models.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "post_id")
	private int IdPost;
	@Column(name= "post_name")
	private String NamePost;
	@Column(name= "responsibility")
	private String Responsibility;
	
	public Post() {};
	public Post(int idPosition, String namePosition, String responsibility) {
		super();
		IdPost = idPosition;
		NamePost = namePosition;
		Responsibility = responsibility;
	}
	public int getIdPost() {
		return IdPost;
	}
	public void setIdPost(int idPost) {
		IdPost = idPost;
	}
	public String getNamePost() {
		return NamePost;
	}
	public void setNamePost(String namePost) {
		NamePost = namePost;
	}
	public String getResponsibility() {
		return Responsibility;
	}
	public void setResponsibility(String responsibility) {
		Responsibility = responsibility;
	}

}
