package app.base.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CommentFormBean implements CommentBean{
    
    @NotNull(message = "User ID is required")
    private long id;
    
    @NotNull(message = "Post ID is required")
    private long postId;
    
    @NotNull(message = "User ID is required")
    private long userId;
    
    @Min(value = 1, message = "Content must not be empty")
    @Max(value = 255, message = "Content should not exceed 255 characters")
    private String content;
    
    @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm")
    @Past(message = "Invalid post date")
    private long time;
    
    @Override
    public String getCommentString() {
        return this.content;
    }

    @Override
    public String getCommentStringPreview() {
        // TODO Auto-generated method stub
        return this.content;
    }

}
