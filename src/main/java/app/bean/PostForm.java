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

	@NotEmpty(message = "Please write something.")
//	@Min(value = 1, message = "Content must not be empty.")
//	@Max(value = 255, message = "Content should not exceed 255 characters.")
	private String content;

	
	private long userId;
	// @NotNull(message = "Upvote value is required.")
	@Min(value = 0, message = "Minimum value is 0.")
	private int upvotes;


}
