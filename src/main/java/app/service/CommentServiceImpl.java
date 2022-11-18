package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.base.service.CommentService;
import app.entity.Comment;
import app.entity.Post;
import app.entity.User;
import app.repository.CommentRepository;
import app.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;

	@Transactional
	@Override
	public void createComment(User user, Comment comment) {
		final Optional<Post> existingPost = postRepository.findById(comment.getPost().getId());
		if (!existingPost.isPresent()) {
			throw new RuntimeException("Post not found.");
		}
		if (!(user.getId() == comment.getUser().getId())) {
			throw new RuntimeException("Different User");
		}

		commentRepository.save(comment);
	}

	@Transactional
	@Override
	public void deletePostComments(User user, Post post) {
		final Optional<Post> existingPostComment = postRepository.findById(post.getId());
		if (!existingPostComment.isPresent()) {
			throw new RuntimeException("Post not found.");
		}
		if (!(user.getId() == post.getUser().getId())) {
			throw new RuntimeException("You are not authorized to remove content");
		}
		commentRepository.deleteByPostId(post.getId());
	}

	@Override
	public List<Comment> getCommentFromPost(Post post) {
		return commentRepository.getByPostId(post.getId());

	}

}