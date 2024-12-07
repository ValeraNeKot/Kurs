package main.Models.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;


public class Post implements Serializable{
	@Expose
	private int IdPost;
	@Expose
	private String NamePost;
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
