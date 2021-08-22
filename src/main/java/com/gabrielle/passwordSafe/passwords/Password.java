package com.gabrielle.passwordSafe.passwords;

import javax.persistence.*;

import com.gabrielle.passwordSafe.users.User;


@Entity
@Table(name="password")
public class Password {
	
	@Id
    @Column(name = "pwd_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer Id;
=======
    private Integer id;
>>>>>>> 43f5f96c4f8f3dac0af4027a4952b760299da995

	@Column(name = "pwd_name")
    private String name;
    
    @Column(name = "pwd_password")
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private User user;
    
    
    public Password() {}
    
    public Password(String name,String  password, User user) {
        this.name = name;
        this.password = password;
        this.user = user;
    }
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
<<<<<<< HEAD
	
=======
>>>>>>> 43f5f96c4f8f3dac0af4027a4952b760299da995
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isValid() {
        return name != null && password != null;
    }
    	
}