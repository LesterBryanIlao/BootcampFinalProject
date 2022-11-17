package app.bean;

import java.util.Date;

import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;
import lombok.Data;

@Data
public class PostForm {
	@NotEmpty
	private long existingPostId;

	@NotEmpty(message = "Please write something.")
	private String content;
	
	private long userId;
	
	@Min(value = 0, message = "Minimum value is 0.")
	private int upvotes;


}
