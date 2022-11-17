package app.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CommentForm {
	@NotEmpty
	private Long existingCommentId;

	@NotNull(message = "Post ID is required")
	private Long userId;

	@NotNull(message = "User ID is required")
	private Long postId;

	@NotNull(message = "Please write something.")
	@Min(value = 1, message = "Comment must not be empty")
	@Max(value = 255, message = "Comment should not exceed 255 characters")
	private String comment;
	
	private Date time;
	
	public String getFormattedTime() {
		SimpleDateFormat formatter= new SimpleDateFormat();
		return String.format("Date posted: %s", formatter.format(time));
	}

	public String getCommentString() {
		// TODO Auto-generated method stub
		return this.comment;
	}

	public String getCommentStringPreview() {
		// TODO Auto-generated method stub
		return this.comment.substring(0, 50) + "...";
	}
}
