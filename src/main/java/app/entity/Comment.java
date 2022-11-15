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

	public Comment(long id, long userId, long postId, String content, long time) {
		this.id = id;
		this.userId = userId;
		this.postId = postId;
		this.content = content;
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public long getUserId() {
		return userId;
	}

	public long getPostId() {
		return postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
