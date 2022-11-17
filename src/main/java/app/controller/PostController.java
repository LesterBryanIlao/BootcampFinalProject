package app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.base.service.CommentService;
import app.base.service.PostService;
import app.entity.Post;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPost(ModelMap modelMap, @RequestParam("postId") int postId ) {
		Post selectedPost = postService.getPostById(postId);
		//insert logic for return "not existing post" if null
		modelMap.addAttribute("post", selectedPost);
		modelMap.addAttribute("comments", commentService.getCommentFromPost(selectedPost));
		return new ModelAndView("post");
	}
}