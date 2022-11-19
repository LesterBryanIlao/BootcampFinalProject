package app.controller;

import java.util.Date;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.base.service.PostService;
import app.base.service.UserAccountManagementService;
import app.base.service.UserSessionManagementService;
import app.bean.PostForm;
import app.entity.Post;
import app.entity.User;

@Controller
@RequestMapping("/postForm")
public class PostFormController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserAccountManagementService userAccountManagementService;
	
	@Autowired
	private UserSessionManagementService userSessionManagementService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(@RequestParam(name = "postId", defaultValue = "0") long postId, ModelMap modelMap) {

		ModelAndView modelAndView = null;
		try {
			User existingUser = userSessionManagementService.getCurrentLoggedInUser(null);
			if (existingUser == null) {
				throw new Exception("Invalid user or not currently logged in");
			}

			PostForm postForm = new PostForm();
			postForm.setExistingPostId(postId);
			postForm.setUserId(existingUser.getId());
			postForm.setExistingPostId(postId);

			Post post = postService.getPostById(postId);
			if (post != null) {
				if (post.getUser().getId() != existingUser.getId()) {
					throw new Exception("You don't have the permission to do the action");
				}

				postForm.setContent(post.getContent());
				postForm.setUpvotes(post.getUpvotes());
				postForm.setUpvotes(post.getUpvotes());
			}

			modelMap.addAttribute("postForm", postForm);
			modelAndView = new ModelAndView("postForm");

		} catch (Exception e) {
			modelAndView = new ModelAndView(String.format("redirect:error?error=%s", e.getMessage()));
		}

		return modelAndView;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult,
			Model model) {

		try {
			if (bindingResult.hasErrors()) {
				throw new IllegalArgumentException();
			}

			User existingUser = userAccountManagementService.getUserById(postForm.getUserId());

			Post post = createPostInstance(existingUser, postForm.getContent());

			long existingId = postForm.getExistingPostId();
			if (existingId > 0) {
				post.setId(existingId);
			}

			postService.createPost(existingUser, post);

		} catch (Exception e) {
			model.addAttribute("error", "Unexpected error while creating the post");
			return "postForm";
		}
		return "redirect:home";
	}

	private Post createPostInstance(User user, String content) {
		Post post = new Post();
		post.setUser(user);
		post.setContent(content);
		post.setUpvotes(0);
		post.setTime(new Date());
		post.setUser(user);
		return post;
	}

}