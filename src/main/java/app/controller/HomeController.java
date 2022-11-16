package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import app.base.service.PostService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPosts(ModelMap modelMap) {
		modelMap.addAttribute("posts", postService.getPosts());
		return new ModelAndView("home");
	}

}
