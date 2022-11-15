package app.entity;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class User {

	private long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;



}
