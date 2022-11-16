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
import app.base.service.UserSessionManagementService;
import app.entity.Post;
import app.entity.User;

@Controller
@RequestMapping("/posts")
public class PostsController {
	@Autowired
	private PostService postService;

	@Autowired
	private UserSessionManagementService userSessionManagementService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPosts(HttpServletRequest request, ModelMap modelMap) {
		String userId = request.getAttribute("userId").toString();
		List<Post> posts = null;
		if (userId == null) {
			posts = postService.getPosts();
		} else {
			User dummyUser = userSessionManagementService.getCurrentLoggedInUser(request);
			posts = postService.getUserPosts(dummyUser);
		}
		modelMap.addAttribute("posts", posts);
		return new ModelAndView("posts");
	}

}
