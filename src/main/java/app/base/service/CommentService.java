package app.base.service;

import java.util.List;

import app.entity.Comment;
import app.entity.Post;
import app.entity.User;

public interface CommentService {

	public void createComment(Post post, Comment comment, String content);

	public void deleteComment(Comment comment);

	public void deletePostComments(Post post);

	public void updateComment(Comment comment, Comment newComment);

	public List<Comment> getCommentFromPost(Post post);

}
