package app.bean;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class PostForm {
	@NotEmpty()
	private String existingPostId;
	
	@NotEmpty(message = "Please write something")
	private String content;

}
