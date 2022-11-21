package app.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
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
import app.base.service.UserSessionManagementService;
import app.bean.CommentForm;
import app.bean.PostDeleteForm;
import app.bean.UpvoteForm;
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

	@Autowired
	private UserSessionManagementService userSessionManagementService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPost(@RequestParam("postId") long postId, ModelMap modelMap) {

		Post selectedPost = postService.getPostById(postId);
		CommentForm commentForm = new CommentForm();
		PostDeleteForm postDeleteForm = new PostDeleteForm();
		UpvoteForm upvoteForm = new UpvoteForm();
		User user = userSessionManagementService.getCurrentLoggedInUser(null);

		List<Comment> commentsList = commentService.getCommentFromPost(selectedPost);

		modelMap.addAttribute("user", user);
		modelMap.addAttribute("post", selectedPost);
		modelMap.addAttribute("deleteForm", postDeleteForm);
		modelMap.addAttribute("upvoteForm", upvoteForm);

		modelMap.addAttribute("commentForm", commentForm);
		modelMap.addAttribute("comments", commentsList);
		return new ModelAndView("post");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitCommentForm(@Valid @ModelAttribute("commentForm") CommentForm commentForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "commentForm";
		}
		try {
			User existingUser = userSessionManagementService.getCurrentLoggedInUser(null);
			Comment comment = createCommentInstance(existingUser, commentForm.getContent(),
					postService.getPostById(commentForm.getPostId()));
			commentService.createComment(existingUser, comment);

		} catch (EntityNotFoundException e) {
			model.addAttribute("error", "Need to login");
			return getRedirectString(commentForm.getPostId());
		} catch (Exception e) {
			model.addAttribute("error", "Unexpected error while creating the post");
			return getRedirectString(commentForm.getPostId());
		}
		return getRedirectString(commentForm.getPostId());
	}

	@RequestMapping(value = "deletePost", method = RequestMethod.POST)
	public String submitDeleteForm(@ModelAttribute("deleteForm") PostDeleteForm deleteForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "deleteForm";
		}

		try {
			User currentUser = userSessionManagementService.getCurrentLoggedInUser(null);
			Post post = postService.getPostById(deleteForm.getPostId());

			commentService.deletePostComments(currentUser, post);
			postService.deletePost(post.getUser(), post);

		} catch (Exception e) {
			return "redirect:/app/error?error=Unexpected error while deleting the post";
		}
		return "redirect:/app/home";
	}

	@RequestMapping(value = "upvotePost", method = RequestMethod.POST)
	public String submitUpvoteForm(@ModelAttribute("upvoteForm") UpvoteForm upvoteForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "upvoteForm";
		}

		try {
			User currentUser = userSessionManagementService.getCurrentLoggedInUser(null);
			Post post = postService.getPostById(upvoteForm.getPostId());

			postService.upVotePost(currentUser, post);

		} catch (Exception e) {
			return String.format("redirect:error?error=%s", "Unexpected error while deleting the post");
		}
		return String.format("redirect:/app/post?postId=%s", upvoteForm.getPostId());
	}

	public Comment createCommentInstance(User user, String content, Post post) {
		Comment comment = new Comment();
		comment.setUser(user);
		comment.setContent(content);
		comment.setPost(post);
		comment.setTime(new Date());
		return comment;
	}

	public String getRedirectString(long postId) {
		return String.format("redirect:post?postId=%s", postId);
	}

}