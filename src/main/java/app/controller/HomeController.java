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
import app.base.service.UserSessionManagementService;
import app.entity.Post;
import app.entity.User;
import app.repository.PostRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private PostService postService;

	@Autowired
	private UserSessionManagementService userSessionManagementService;
	
	@Autowired
	PostRepository postRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPosts(HttpServletRequest request, ModelMap modelMap, @RequestParam(value ="userID", required = false)String userId) {
		Object userIdData = request.getAttribute("userId");
		List<Post> posts = new ArrayList<>();
		if (userIdData == null) {
			posts = postRepository.findAll();

		} else {
			User dummyUser = userSessionManagementService.getCurrentLoggedInUser(request);
			posts = postService.getUserPosts(dummyUser);
		}
		modelMap.addAttribute("posts", posts);
		return new ModelAndView("home");
	}

}
