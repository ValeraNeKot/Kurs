package condorcet.Models.Entities;

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

@Entity
@Table(name = "vacancy")
public class Vacancy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id") // Добавьте уникальный идентификатор для Vacancy
    @Expose
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_id")
    @Expose
    private Post post;

    @Column(name = "number")
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
