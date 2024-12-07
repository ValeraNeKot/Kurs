package condorcet.Models.Entities;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import condorcet.Enums.Roles;

@Entity
@Table(name="user")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	@Expose
    private int IdAccount;
	@Column(name="login",length = 45,nullable = false)
	@Expose
    private String Login;
	@Column(name="password",length = 45, nullable = false)   
	@Expose
    private String Password;
	@Column(name="role",length = 45, nullable = false)   
	@Enumerated(EnumType.STRING) 
	@Expose
    private Roles role;
	 @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 @Expose
	    private Specialist specialist;
    public User(){
    }
    
    public User(int id, String login, String password, Roles role, Specialist specialist) {
        IdAccount = id;
        Login = login;
        Password = password;
        this.role = role;
        this.specialist=specialist;
    }
    
    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }
    
    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    
    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public int getIdAccount() {
        return IdAccount;
    }

    public void setIdAccount(int id) {
        IdAccount = id;
    }
    
}
