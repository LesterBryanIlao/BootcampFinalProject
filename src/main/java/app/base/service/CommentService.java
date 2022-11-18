package app.base.service;

import java.util.List;

import app.entity.Comment;
import app.entity.Post;
import app.entity.User;

public interface CommentService {

	public void createComment(User user, Comment comment);

	public void deletePostComments(User user, Post post);

	public List<Comment> getCommentFromPost(Post post);

}
