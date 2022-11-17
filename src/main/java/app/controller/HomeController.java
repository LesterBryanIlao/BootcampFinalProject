package app.controller;

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
import app.base.service.UserService;
import app.base.service.UserSessionManagementService;
import app.entity.Post;
import app.entity.User;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPosts(HttpServletRequest request, ModelMap modelMap,
			@RequestParam("userId") String userId) {

		List<Post> posts = null;
		if (userId == null) {
			posts = this.getAllPosts();
		} else {
			try {
				User dummyUser = userService.getUserById(Long.parseLong(userId));
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
