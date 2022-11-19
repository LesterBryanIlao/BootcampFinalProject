package app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.base.service.PostService;
import app.base.service.UserAccountManagementService;
import app.entity.Post;
import app.entity.User;
import app.repository.PostRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private PostService postService;

	@Autowired
	private UserAccountManagementService userAccountManagementService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPosts(ModelMap modelMap,
			@RequestParam(value="userId", required=false, defaultValue = "0") long userId) {

		List<Post> posts = null;
		if (userId == 0) {
			posts = this.getAllPosts();
		} else {
			try {
				User dummyUser = userAccountManagementService.getUserById(userId);
				posts = postService.getUserPosts(dummyUser);
			} catch (Exception e) {
				posts = this.getAllPosts();
			}
		}
		modelMap.addAttribute("posts", posts);
		return new ModelAndView("home");
	}

	private List<Post> getAllPosts() {
		return postService.getPosts();
	}

}
