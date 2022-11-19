package app.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

import app.base.service.PostService;
import app.base.service.UserAccountManagementService;
import app.bean.PostForm;
import app.entity.User;

public class PostFormControllerTest {
	@Mock
	private BindingResult bindingResult = mock(BindingResult.class);
	@Mock
	private UserAccountManagementService userAccountManagementService = mock(UserAccountManagementService.class);
	@Mock
	private Model model = mock(Model.class);
	@Mock
	private PostService postService = mock(PostService.class);

	private PostFormController postFormController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		postFormController = new PostFormController();

		Whitebox.setInternalState(postFormController, "userAccountManagementService", userAccountManagementService);
		Whitebox.setInternalState(postFormController, "postService", postService);
	}

	@Test
	public void show_form_method_view_name_should_be_equal_to_postForm() {
		long userId = 1;
		long postId = 1;
		ModelAndView modelAndView = postFormController.showForm(userId, postId, new ModelMap());
		assertTrue(modelAndView.getViewName().equals("postForm"));
	}

	@Test
	public void show_form_method_should_return_the_passed_user_id_inside_a_PostForm_model() {
		ModelMap modelMap = new ModelMap();
		long userId = 10;
		long postId = 1;
		postFormController.showForm(userId, postId, modelMap);
		assertTrue(((PostForm) modelMap.get("postForm")).getUserId() == userId);
	}

	@Test
	public void submit_form_method_should_return_home_when_successful() throws Exception {
		User user = new User();
		boolean bindingResultHasErrors = false;
		String expectedReturnPath = "home";
		executeSubmitFormFlow(user, bindingResultHasErrors, expectedReturnPath);
	}

	@Test
	public void submit_form_method_should_return_postform_when_user_dont_exists() {
		User user = null;
		boolean bindingResultHasErrors = false;
		String expectedReturnPath = "postForm";
		executeSubmitFormFlow(user, bindingResultHasErrors, expectedReturnPath);
	}

	@Test
	public void submit_form_method_should_return_postform_when_binding_result_has_error() {
		User user = new User();
		boolean bindingResultHasErrors = true;
		String expectedReturnPath = "postForm";
		executeSubmitFormFlow(user, bindingResultHasErrors, expectedReturnPath);
	}

	private void executeSubmitFormFlow(User postOwner, boolean bindingResultHasErrors, String expectedReturnPath) {
		PostForm postForm = new PostForm();
		postForm.setUserId(10);

		when(bindingResult.hasErrors()).thenReturn(bindingResultHasErrors);

		OngoingStubbing<User> getByUserIdStubbing = when(
				userAccountManagementService.getUserById(postForm.getUserId()));
		if (postOwner == null) {
			getByUserIdStubbing.thenThrow(EntityNotFoundException.class);
		} else {
			getByUserIdStubbing.thenReturn(postOwner);
		}

		String result = postFormController.submitForm(postForm, bindingResult, model);

		assertTrue(result == expectedReturnPath);
	}
}
