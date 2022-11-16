package app.base.bean;

import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class CommentForm {
    @NotNull(message = "Comment Id is needed first!")
    private Long commentId;
    
    @NotNull(message = "User Id is needed first!")
    private Long userId;
    
    @NotNull(message = "Post Id enter a message first!")
    private Long postId;
    
    @NotNull(message = "Please enter a message first!")
    private String commentContent;

}
