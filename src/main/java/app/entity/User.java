package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "RedditUserDetails")
@Entity
@Data
public class User {

    @Id
    @Column(name="user_id")
	private long id;
    
    @Column(name = "user_name", columnDefinition = "VARCHAR(30)", nullable = false)
	private String userName;
    
    @Column(name="first_name", columnDefinition = "VARCHAR(20)", nullable = false)
	private String firstName;
    
    @Column(name ="last_name", columnDefinition = "VARCHAR(20)", nullable = false)
	private String lastName;
    
    @Column(name ="user_password", columnDefinition = "VARCHAR(20)", nullable = false)
	private String password;



}
