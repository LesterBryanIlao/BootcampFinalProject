package app.base.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostFormBean implements PostBean{

	@NotNull(message = "Post ID  is required.")
	private long id;

	@NotNull(message = "User ID is required.")
	private long userId;

	@Min(value = 1, message = "Content must not be empty.")
	@Max(value = 255, message = "Content should not exceed 255 characters.")
	private String postContent;

	@DateTimeFormat(pattern = "MM/dd/yyyy hh:mm")
	@Past(message = "Invalid post date")
	private long time;

	//@NotNull(message = "Upvote value is required.")
	@Min(value = 0, message = "Minimum value is 0.")
	private int upvotes;

	@Override
	public String getStringPost() {
		// TODO Auto-generated method stub
		return this.postContent;
	}

	@Override
	public String getStringPostPreview() {
		// TODO Auto-generated method stub
		return this.postContent.substring(0, 50) + "...";
	}
	
}
