package app.controller;

import java.util.Date;

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

import app.base.service.CommentService;
import app.base.service.PostService;
import app.bean.CommentForm;
import app.entity.Comment;
import app.entity.Post;
import app.entity.User;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPost(ModelMap modelMap, @RequestParam("postId") int postId) {
		Post selectedPost = postService.getPostById(postId);
		// insert logic for return "not existing post" if null
		modelMap.addAttribute("post", selectedPost);
		modelMap.addAttribute("comments", commentService.getCommentFromPost(selectedPost));
		return new ModelAndView("post");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute("commentForm") CommentForm commentForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "commentForm";
		}
		try {

			Comment comment = createCommentInstance(null, commentForm.getCommentString(),
					postService.getPostById(commentForm.getPostId()));
			commentService.createComment(null, comment);
		} catch (Exception e) {
			model.addAttribute("userError", e.getMessage());
			return "commentForm";

		}
		return "redirect:post";
	}

	public Comment createCommentInstance(User user, String content, Post post) {
		Comment comment = new Comment();
		
		comment.setUser(user);
		comment.setContent(content);
		comment.setPost(post);
		comment.setTime(new Date());
		return comment;

	}

}