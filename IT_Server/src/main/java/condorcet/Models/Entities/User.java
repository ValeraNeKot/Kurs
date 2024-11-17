package condorcet.Models.Entities;

import javax.persistence.*;

import main.Enums.Roles;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
    private int IdAccount;
	@Column(name="login",length = 45,nullable = false)
    private String Login;
	@Column(name="password",length = 45, nullable = false)   
    private String Password;
	@Column(name="role",length = 45, nullable = false)   
    private Roles role;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
