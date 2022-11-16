package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import app.base.service.PostService;
import app.entity.Post;
import app.entity.User;

@Controller
@RequestMapping("/posts")
public class PostsController {
	@Autowired
	private PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPosts(HttpServletRequest request, ModelMap modelMap) {
		String userId = request.getAttribute("userId").toString();
		List<Post> posts = null;
		if (userId == null) {
			posts = postService.getPosts();
		} else {
			User dummyUser = new User();
			dummyUser.setId(10);
			dummyUser.setFirstName("User_10");
			dummyUser.setLastName("Doe");
			dummyUser.setPassword("Test");
			posts = postService.getUserPosts(dummyUser);
		}
		modelMap.addAttribute("posts", posts);
		return new ModelAndView("home");
	}

}
