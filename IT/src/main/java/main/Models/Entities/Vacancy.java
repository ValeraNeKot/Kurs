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

import javafx.beans.property.IntegerProperty;

public class Vacancy implements Serializable {
    @Expose
    private int id;
    @Expose
    private Post post;
    @Expose
    private int number;
    
    public javafx.beans.property.IntegerProperty idProperty() {
        return new javafx.beans.property.SimpleIntegerProperty(id);
    }

    public javafx.beans.property.StringProperty postProperty() {
        return new javafx.beans.property.SimpleStringProperty(post.getNamePost());
    }

    public javafx.beans.property.IntegerProperty numberProperty() {
        return new javafx.beans.property.SimpleIntegerProperty(number);
    }
    
	public Vacancy() {};
	
	public Vacancy(Post post, int number) {
		super();
		this.post = post;
		this.number = number;
	}
	
	public Vacancy(int id, Post post, int number) {
		super();
		this.id =id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
