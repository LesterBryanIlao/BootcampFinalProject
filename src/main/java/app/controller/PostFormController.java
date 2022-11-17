package app.controller;

import java.util.Date;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;
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
import app.base.service.UserAccountManagementService;
import app.base.service.UserSessionManagementService;
import app.bean.PostForm;
import app.entity.Post;
import app.entity.User;
import javassist.expr.NewArray;

@Controller
@RequestMapping("/postForm")
public class PostFormController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserAccountManagementService userAccountManagementService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(ModelMap modelMap) {
		modelMap.addAttribute("postForm", new PostForm());
		return new ModelAndView("postForm");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Unexpected error while creating the post");
			return "postForm";
		}

		try {

			User existingUser = userAccountManagementService.getUserById(postForm.getUserId());

			Post post = createPostInstance(existingUser, postForm.getContent());

			String existingPostIdString = postForm.getExistingPostId();
			if (!existingPostIdString.isEmpty()) {
				post.setId(Long.parseLong(existingPostIdString));
			}

			postService.createPost(existingUser, post);

		} catch (EntityNotFoundException e) {
			model.addAttribute("error", "Need to login");
			return "postForm";
		}
		return "home";
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
