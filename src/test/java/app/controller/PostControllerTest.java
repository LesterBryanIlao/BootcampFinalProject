package app.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import app.base.service.CommentService;
import app.base.service.PostService;
import app.base.service.UserSessionManagementService;
import app.bean.CommentForm;
import app.bean.PostDeleteForm;
import app.entity.Comment;
import app.entity.Post;
import app.entity.User;

public class PostControllerTest {
	@Mock
	private BindingResult bindingResult = mock(BindingResult.class);
	@Mock
	private UserSessionManagementService userSessionManagementService = mock(UserSessionManagementService.class);
	@Mock
	private Model model = mock(Model.class);
	@Mock
	private PostService postService = mock(PostService.class);
	@Mock
	private CommentService commentService = mock(CommentService.class);

	private PostController postController;

	private ModelAndView modelAndView;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		postController = new PostController();
		Whitebox.setInternalState(postController, "postService", postService);
		Whitebox.setInternalState(postController, "userSessionManagementService", userSessionManagementService);
		Whitebox.setInternalState(postController, "commentService", commentService);

	}

	@Test
	public void show_form_method_should_be_equal_to_post_when_successful() {
		long userId = 1L;
		long postId = 1L;

		User user = mock(User.class);
		Post post = mock(Post.class);

		when(userSessionManagementService.getCurrentLoggedInUser(null)).thenReturn(user);
		when(postService.getPostById(postId)).thenReturn(post);
		when(post.getUser()).thenReturn(user);
		when(user.getId()).thenReturn(userId);
		when(post.getContent()).thenReturn("");

		when(commentService.getCommentFromPost(post)).thenReturn(new ArrayList<Comment>());
		modelAndView = postController.showPost(postId, new ModelMap());
		assertTrue(modelAndView.getViewName().equals("post"));
	}

	@Test
	public void submit_comment_form_should_redirect_when_error() {
		long postId = 0;
		User user = new User();
		boolean bindingResultHasErrors = false;
		String expectedReturnPath = "redirect:post?postId=" + postId;
		executeSubmitCommentFormFlow(user, bindingResultHasErrors, expectedReturnPath);
	}

	@Test
	public void submit_comment_form_should_return_commentForm_when_user_does_not_exist() {
		User user = new User();
		boolean bindingResultHasErrors = true;
		String expectedReturnPath = "commentForm";
		executeSubmitCommentFormFlow(user, bindingResultHasErrors, expectedReturnPath);
	}

	@Test
	public void submit_delete_form_should_redirect_to_home() {
		User user = new User();
		boolean bindingResultHasErrors = false;
		String expectedReturnPath = "redirect:/app/error?error=Unexpected error while deleting the post";
		executeSubmitDeleteFormFlow(user, bindingResultHasErrors, expectedReturnPath);
	}

	@Test
	public void submit_delete_form_should_return_deleteForm_when_user_does_not_exist() {
		User user = new User();
		boolean bindingResultHasErrors = true;
		String expectedReturnPath = "deleteForm";
		executeSubmitDeleteFormFlow(user, bindingResultHasErrors, expectedReturnPath);
	}

	private void executeSubmitCommentFormFlow(User postOwner, boolean bindingResultHasErrors,
			String expectedReturnPath) {
		CommentForm commentForm = new CommentForm();

		when(bindingResult.hasErrors()).thenReturn(bindingResultHasErrors);

		OngoingStubbing<User> getByUserIdStubbing = when(userSessionManagementService.getCurrentLoggedInUser(null));
		if (postOwner == null) {
			getByUserIdStubbing.thenThrow(EntityNotFoundException.class);
		} else {
			getByUserIdStubbing.thenReturn(postOwner);
		}

		String result = postController.submitCommentForm(commentForm, bindingResult, model);

		assertEquals(result, expectedReturnPath);
	}

	private void executeSubmitDeleteFormFlow(User postOwner, boolean bindingResultHasErrors,
			String expectedReturnPath) {
		PostDeleteForm deleteForm = new PostDeleteForm();

		when(bindingResult.hasErrors()).thenReturn(bindingResultHasErrors);

		OngoingStubbing<User> getByUserIdStubbing = when(userSessionManagementService.getCurrentLoggedInUser(null));
		if (postOwner == null) {
			getByUserIdStubbing.thenThrow(Exception.class);
		} else {
			getByUserIdStubbing.thenReturn(postOwner);
		}

		String result = postController.submitDeleteForm(deleteForm, bindingResult, model);

		assertEquals(result, expectedReturnPath);
	}

}
