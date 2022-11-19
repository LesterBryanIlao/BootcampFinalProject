package app.bean;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentForm {
//    @NotEmpty
	private long existingCommentId;

//    @NotNull(message = "Post ID is required")
	private long postId;

	@NotNull(message = "Please write something.")
//    @Min(value = 1, message = "Comment must not be empty")
//    @Max(value = 255, message = "Comment should not exceed 255 characters")
	private String content;

//    private Date time;

//    public String getFormattedTime() {
//        SimpleDateFormat formatter = new SimpleDateFormat();
//        return String.format("Date posted: %s", formatter.format(time));
//    }
//
//    public String getCommentString() {
//        // TODO Auto-generated method stub
//        return this.content;
//    }
//
//    public String getCommentStringPreview() {
//        // TODO Auto-generated method stub
//        return this.content.substring(0, 50) + "...";
//    }
}