package app.bean;

//import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CommentForm {
//	@NotEmpty
	private long existingCommentId;

//	@NotNull(message = "User ID is required")
	private long userId;

//	@NotNull(message = "Post ID is required")
	private long postId;

	@NotNull(message = "Please write something.")
	@Min(value = 1, message = "Comment must not be empty")
	@Max(value = 255, message = "Comment should not exceed 255 characters")
	private String content;

//	private Date time;

//	public String getFormattedTime() {
//		SimpleDateFormat formatter = new SimpleDateFormat();
//		return String.format("Date posted: %s", formatter.format(time));
//	}
//
//	public String getCommentString() {
//		// TODO Auto-generated method stub
//		return this.content;
//	}
//
//	public String getCommentStringPreview() {
//		// TODO Auto-generated method stub
//		return this.content.substring(0, 50) + "...";
//	}
}
