package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "Users")
@Entity
@Data
public class User implements Serializable {
	private static final long serialVersionUID = -3593227270390705354L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", columnDefinition = "INT", nullable = false)
	private long id;

	@Column(name = "user_name", columnDefinition = "VARCHAR(30)", nullable = false)
	private String userName;

	@Column(name = "first_name", columnDefinition = "VARCHAR(20)", nullable = false)
	private String firstName;

	@Column(name = "last_name", columnDefinition = "VARCHAR(20)", nullable = false)
	private String lastName;

	@Column(name = "password_hash", columnDefinition = "VARCHAR(256)", nullable = false)
	private String passwordHash;

	@Column(name = "email", columnDefinition = "VARCHAR(20)", nullable = false)
	private String email;

}
