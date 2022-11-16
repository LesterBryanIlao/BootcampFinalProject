package app.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import app.base.service.PostService;
import app.bean.PostForm;
import app.entity.Post;
import app.entity.User;

@Controller
public class PostFormController {
	private final PostService postService;
	private final static String POST_CREATE_ERROR = "Unexpected error occured while creating post";

	public PostFormController(PostService postService) {
		this.postService = postService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(ModelMap modelMap) {
		modelMap.addAttribute("postForm", new PostForm());
		return new ModelAndView("postForm");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "postForm";
		}

		try {
			User dummyUser = new User();
			dummyUser.setId(10);
			dummyUser.setFirstName("User_10");
			dummyUser.setLastName("Doe");
			dummyUser.setPassword("Test");

			if (postForm.getExistingPostId() == null) {
				Post post = new Post();
				post.setContent(postForm.getContent());
				post.setUpvotes(0);
				post.setTime(System.currentTimeMillis());
				postService.createPost(dummyUser, post);
			} else {
				long postid = Long.parseLong(postForm.getExistingPostId());
				Post existingPost = postService.getByPostId(postid);

				if (existingPost == null) {
					model.addAttribute("postCreateError", POST_CREATE_ERROR);
				} else {
					postService.updatePostContent(dummyUser, existingPost);
				}

			}
		} catch (Exception e) {
			model.addAttribute("postCreateError", POST_CREATE_ERROR);
		}

		return "home";
	}

}
