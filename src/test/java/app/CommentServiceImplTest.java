package app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import app.entity.Comment;
import app.entity.Post;
import app.entity.User;
import app.repository.CommentRepository;
import app.repository.PostRepository;
import app.service.CommentServiceImpl;

public class CommentServiceImplTest {

	@Mock
	private PostRepository postRepository = mock(PostRepository.class);
	@Mock
	private CommentRepository commentRepository = mock(CommentRepository.class);

	@Mock
	private CommentServiceImpl mockedCommentServiceImpl = mock(CommentServiceImpl.class);

	private CommentServiceImpl commentServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		commentServiceImpl = new CommentServiceImpl();

		Whitebox.setInternalState(commentServiceImpl, "postRepository", postRepository);
		Whitebox.setInternalState(commentServiceImpl, "commentRepository", commentRepository);

	}

	@Test(expected = IllegalArgumentException.class)
	public void create_comment_with_different_user_should_throw_illegal_argument_exception() {
		Post post = new Post();
		post.setId(1);

		User user1 = new User();
		user1.setId(1);

		User user2 = new User();
		user2.setId(2);

		Comment comment = new Comment();
		comment.setUser(user2);

		comment.setPost(post);

		when(postRepository.findById(1L)).thenReturn(Optional.of(post));
		commentServiceImpl.createComment(user1, comment);

	}

	@Test(expected = Test.None.class)
	public void create_comment_with_same_user_should_be_successful() {
		Post post = new Post();
		post.setId(1);

		User user1 = new User();
		user1.setId(1);

		Comment comment = new Comment();
		comment.setUser(user1);

		comment.setPost(post);

		when(postRepository.findById(1L)).thenReturn(Optional.of(post));

		commentServiceImpl.createComment(user1, comment);

	}

	@Test(expected = IllegalArgumentException.class)
	public void create_comment_for_non_existent_post_should_throw_illegal_argument_exception() {
		Post post = new Post();
		post.setId(1);

		User user = new User();
		user.setId(1);

		Comment comment = new Comment();
		comment.setUser(user);

		comment.setPost(post);

		when(postRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		commentServiceImpl.createComment(user, comment);
	}

	@Test(expected = Test.None.class)
	public void create_comment_existent_post_should_succeed() {
		Post post = new Post();
		post.setId(1);

		User user = new User();
		user.setId(1);

		Comment comment = new Comment();
		comment.setUser(user);

		comment.setPost(post);

		when(postRepository.findById(1L)).thenReturn(Optional.of(post));
		commentServiceImpl.createComment(user, comment);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleting_not_owned_post_comments_should_throw_exception() {
		Post post = new Post();
		post.setId(1);

		User user1 = new User();
		user1.setId(1);

		User user2 = new User();
		user2.setId(2);

		post.setUser(user2);

		when(postRepository.findById(1L)).thenReturn(Optional.of(post));
		commentServiceImpl.deletePostComments(user1, post);

	}

	@Test(expected = Test.None.class)
	public void deleting_owned_post_comments_should_succeed() {
		Post post = new Post();
		post.setId(1);

		User user1 = new User();
		user1.setId(1);

		post.setUser(user1);

		when(postRepository.findById(1L)).thenReturn(Optional.of(post));
		commentServiceImpl.deletePostComments(user1, post);

	}
}
