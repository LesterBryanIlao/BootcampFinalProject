package app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Comment {

    @Id
	private long id;
	private long userId;
	private long postId;
	private String content;
	private long time;

	

}
