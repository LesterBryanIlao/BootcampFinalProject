package app;

import static org.mockito.Mockito.mock;
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
	private User currentUser;
	private Post currentPost;
	private Comment currentComment;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		commentServiceImpl = new CommentServiceImpl();

		currentPost = new Post();
		currentPost.setId(1);

		currentUser = new User();
		currentUser.setId(1);

		currentComment = new Comment();
		currentComment.setId(1);

		Whitebox.setInternalState(commentServiceImpl, "postRepository", postRepository);
		Whitebox.setInternalState(commentServiceImpl, "commentRepository", commentRepository);

	}

	@Test(expected = IllegalArgumentException.class)
	public void create_comment_with_different_user_should_throw_illegal_argument_exception() {
		User user2 = new User();
		user2.setId(2);

		currentComment.setUser(user2);
		currentComment.setPost(currentPost);

		mockPostRepositoryFindById(1);
		commentServiceImpl.createComment(currentUser, currentComment);

	}

	@Test(expected = Test.None.class)
	public void create_comment_with_same_user_should_be_successful() {
		currentComment.setUser(currentUser);
		currentComment.setPost(currentPost);

		mockPostRepositoryFindById(1);
		commentServiceImpl.createComment(currentUser, currentComment);

	}

	@Test(expected = IllegalArgumentException.class)
	public void create_comment_for_non_existent_post_should_throw_illegal_argument_exception() {
		currentComment.setUser(currentUser);
		currentComment.setPost(currentPost);

		mockPostRepositoryFindById(1);
		commentServiceImpl.createComment(currentUser, currentComment);
	}

	@Test(expected = Test.None.class)
	public void create_comment_existent_post_should_succeed() {
		currentComment.setUser(currentUser);
		currentComment.setPost(currentPost);

		mockPostRepositoryFindById(1);
		commentServiceImpl.createComment(currentUser, currentComment);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleting_not_owned_post_comments_should_throw_exception() {
		User user2 = new User();
		user2.setId(2);

		currentPost.setUser(user2);

		mockPostRepositoryFindById(1);
		commentServiceImpl.deletePostComments(currentUser, currentPost);
	}

	@Test(expected = Test.None.class)
	public void deleting_owned_post_comments_should_succeed() {
		currentPost.setUser(currentUser);

		mockPostRepositoryFindById(1);
		commentServiceImpl.deletePostComments(currentUser, currentPost);

	}
	
	private void mockPostRepositoryFindById(long id) {
		when(postRepository.findById(id)).thenReturn(Optional.of(currentPost));
		
	}
}
