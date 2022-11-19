package app.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.ui.ModelMap;

import app.base.service.PostService;
import app.base.service.UserAccountManagementService;

public class HomeControllerTest {
	@Mock
	private PostService postService = mock(PostService.class);
	@Mock
	private UserAccountManagementService userAccountManagementService = mock(UserAccountManagementService.class);

	private HomeController homeController;

	@Before
	private void setup() {
		MockitoAnnotations.initMocks(this);
		homeController = new HomeController();
		Whitebox.setInternalState(homeController, "postService", postService);
		Whitebox.setInternalState(homeController, "userAccountManagementService", userAccountManagementService);
	}

	@Test
	private void show_post_should_return_view_with_name_of_home_if_provided_userid_is_0() {
		showPostsShouldHaveModelViewWithNameHome(0);
	}

	@Test
	private void show_post_should_return_view_with_name_of_home_if_provided_userid_is_non_0() {
		showPostsShouldHaveModelViewWithNameHome(1);
		showPostsShouldHaveModelViewWithNameHome(100);
		showPostsShouldHaveModelViewWithNameHome(12);
		showPostsShouldHaveModelViewWithNameHome(Long.MAX_VALUE);
	}

	@Test
	private void show_post_should_have_posts_attribute_in_model_map_if_provided_user_id_is_0() {
		showPostShouldHavePostsAttributeInModelMap(0);

	}

	@Test
	private void show_post_should_have_posts_attribute_in_model_map_if_provided_user_id_is_non_0() {
		showPostShouldHavePostsAttributeInModelMap(Long.MAX_VALUE);
		showPostShouldHavePostsAttributeInModelMap(Long.MIN_VALUE);
		showPostShouldHavePostsAttributeInModelMap(100);
		showPostShouldHavePostsAttributeInModelMap(-1);

	}

	private void showPostsShouldHaveModelViewWithNameHome(long userId) {
		ModelMap modelMap = new ModelMap();
		String returnPath = homeController.showPosts(modelMap, userId).getViewName();
		assertTrue(returnPath == "home");
	}

	private void showPostShouldHavePostsAttributeInModelMap(long userId) {
		ModelMap modelMap = new ModelMap();
		homeController.showPosts(modelMap, userId).getViewName();
		assertTrue(modelMap.containsAttribute("posts"));
	}

}
