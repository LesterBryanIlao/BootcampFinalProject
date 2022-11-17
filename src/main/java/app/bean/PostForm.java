package app.bean;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;
import lombok.Data;


@Data
public class PostForm {
    
	@NotEmpty()
	private String existingPostId;
	
	@NotEmpty(message = "Please write something")
	private String content;
	
    //@NotNull(message = "Upvote value is required.")
    @Min(value = 0, message = "Minimum value is 0.")
    private int upvotes;	
    


}
