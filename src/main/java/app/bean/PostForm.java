package app.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostForm {
	@NotEmpty
	private String existingPostId;

	@NotNull(message = "User ID is required.")
	private long userId;

	@NotEmpty(message = "Please write something.")
	@Min(value = 1, message = "Content must not be empty.")
	@Max(value = 255, message = "Content should not exceed 255 characters.")
	private String content;

	// @Past(message = "Invalid post date")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@NotNull(message = "Date value is required.")
	private Date time;

	// @NotNull(message = "Upvote value is required.")
	@Min(value = 0, message = "Minimum value is 0.")
	private int upvotes;

	public String getFormattedTime() {
		SimpleDateFormat formatter = new SimpleDateFormat();
		return String.format("Date posted: %s", formatter.format(time));
	}

	public String getStringPost() {
		// TODO Auto-generated method stub
		return this.content;
	}

	public String getStringPostPreview() {
		// TODO Auto-generated method stub
		return this.content.substring(0, 50) + "...";
	}

}
