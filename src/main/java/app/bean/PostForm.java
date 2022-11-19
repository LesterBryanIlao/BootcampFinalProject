package app.bean;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class PostForm {

	private long existingPostId;
	@NotEmpty(message = "Please write something.")
	private String content;
	private long upvotes;

}
