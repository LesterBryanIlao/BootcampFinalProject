package app.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import app.base.service.PostService;
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
	private UserSessionManagementService userSessionManagementService;

	private final static String POST_CREATE_ERROR = "Unexpected error occured while creating post";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(ModelMap modelMap) {
		modelMap.addAttribute("postForm", new PostForm());
		return new ModelAndView("postForm");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute("postForm") PostForm postForm, HttpServletRequest request, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "postForm";
		}

		try {
			User dummyUser = userSessionManagementService.getCurrentLoggedInUser(request);

			String content = postForm.getContent();
			if (postForm.getExistingPostId() == null) {
				Post newPost = createPostInstance(dummyUser, content);
				postService.createPost(dummyUser, newPost);
			} else {
				long postid = Long.parseLong(postForm.getExistingPostId());
				Post existingPost = postService.getPostById(postid);

				if (existingPost != null) {
					existingPost.setContent(content);
					postService.updatePostContent(dummyUser, existingPost);
					return "posts";
				} else {
					model.addAttribute("postCreateError", POST_CREATE_ERROR);
				}

			}
		} catch (Exception e) {
			model.addAttribute("postCreateError", POST_CREATE_ERROR);
		}

		return "postForm";
	}

	private Post createPostInstance(User user, String content) {
		Post post = new Post();
		post.setUser(user);
		post.setContent(content);
		post.setUpvotes(0);
		post.setDatePosted(new Date());
		post.setUser(user);
		return post;
	}
}
