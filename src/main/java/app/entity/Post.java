package app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;

@Table(name = "Posts")
@Entity
@Data
public class Post implements Serializable{

	private static final long serialVersionUID = 9172197894142144897L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id", columnDefinition = "INT", nullable = false)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "post_content", columnDefinition = "VARCHAR(500)")
	private String content;

	@Column(name = "up_votes", columnDefinition = "INT", nullable = false)
	private long upvotes;

	@Column(name = "date_posted", columnDefinition = "DATE", nullable = false)
	private Date time;

}
