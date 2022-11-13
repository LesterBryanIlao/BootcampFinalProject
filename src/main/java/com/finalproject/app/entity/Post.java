package com.finalproject.app.entity;

public class Post {

	private long id;
	private long userId;
	private String content;
	private long upvotes;
	private long time;

	public Post(long id, long userId, String content, long upvotes, long time) {
		super();
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.upvotes = upvotes;
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public long getUserId() {
		return userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(long upvotes) {
		this.upvotes = upvotes;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
