package app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Post {

    @Id
	private long id;
	private long userId;
	private String content;
	private long upvotes;
	private long time;

	

}
