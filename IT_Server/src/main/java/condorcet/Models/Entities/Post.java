package condorcet.Models.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="post")
public class Post implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "post_id")
	@Expose
	private int IdPost;
	@Column(name= "post_name")
	@Expose
	private String NamePost;
	@Column(name= "responsibility")
	@Expose
	private String Responsibility;
	
	public Post() {};
	public Post(int idPosition, String namePosition, String responsibility) {
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
