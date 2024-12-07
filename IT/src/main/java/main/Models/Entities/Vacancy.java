package main.Models.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

public class Vacancy implements Serializable {
    @Expose
    private Long id;
    @Expose
    private Post post;
    @Expose
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
