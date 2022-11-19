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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView showForm(@RequestParam("userId") long userId, @RequestParam("postId") long postId,
			ModelMap modelMap) {
		PostForm postForm = new PostForm();
		postForm.setUserId(userId);
		postForm.setExistingPostId(postId);
		modelMap.addAttribute("postForm", postForm);
		return new ModelAndView("postForm");
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

		} catch (EntityNotFoundException e) {
			model.addAttribute("error", "Need to login");
			return "postForm";
		} catch (Exception e) {
			model.addAttribute("error", "Unexpected error while creating the post");
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
