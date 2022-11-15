package app.entity;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Comment {

	private long id;
	private long userId;
	private long postId;
	private String content;
	private long time;

	

}
