package app.bean;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;


@Data
public class PostForm {
	@NotEmpty()
	private String existingPostId;
	
	@NotEmpty(message = "Please write something")
	private String content;
	
    //@Past(message = "Invalid post date")
	@DateTimeFormat(pattern="dd/mm/yyyy")

	@NotNull(message = "Date value is required.")
    private Date time;

    //@NotNull(message = "Upvote value is required.")
    @Min(value = 0, message = "Minimum value is 0.")
    private int upvotes;	
    


}
