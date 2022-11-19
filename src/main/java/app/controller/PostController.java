package app.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.hc.core5.http.HttpRequest;
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
import app.base.service.UserAccountManagementService;
import app.bean.CommentForm;
import app.bean.PostDeleteForm;
import app.bean.PostForm;
import app.entity.Comment;
import app.entity.Post;
import app.entity.User;
import app.util.CommentsSorter;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserAccountManagementService userAccountManagementService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPost(@RequestParam("userId") long userId, @RequestParam("postId") long postId,
			ModelMap modelMap) {

		Post selectedPost = postService.getPostById(postId);
		CommentForm commentForm = new CommentForm();
		PostDeleteForm postDeleteForm = new PostDeleteForm();
		User user = userAccountManagementService.getUserById(userId);
		
		commentForm.setUserId(userId);
		postDeleteForm.setPostId(selectedPost.getId());
		postDeleteForm.setUserId(user.getId());

		CommentsSorter commentsSorter = new CommentsSorter();
		List<Comment> commentsList = commentService.getCommentFromPost(selectedPost);
		Collections.sort(commentsList, commentsSorter.getByTimeAscendingOrder());

		modelMap.addAttribute("user", user);
		modelMap.addAttribute("post", selectedPost);
		modelMap.addAttribute("deleteForm", postDeleteForm);

//		modelMap.addAttribute("upvoteForm", postForm);
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
			User existingUser = userAccountManagementService.getUserById(commentForm.getUserId());
			Comment comment = createCommentInstance(existingUser, commentForm.getContent(),
					postService.getPostById(commentForm.getPostId()));
			commentService.createComment(existingUser, comment);

		} catch (EntityNotFoundException e) {
			model.addAttribute("error", "Need to login");
			return getRedirectString(commentForm.getUserId(), commentForm.getPostId());
		} catch (Exception e) {
			model.addAttribute("error", "Unexpected error while creating the post");
			return getRedirectString(commentForm.getUserId(), commentForm.getPostId());
		}
		return getRedirectString(commentForm.getUserId(), commentForm.getPostId());
	}

	@RequestMapping(value = "deletePost", method = RequestMethod.POST)
	public String submitDeleteForm(@ModelAttribute("deleteForm") PostDeleteForm deleteForm, HttpServletRequest request,
			BindingResult bindingResult, Model model) {
		
		User user = userAccountManagementService.getUserById(deleteForm.getUserId());
		Post post = postService.getPostById(deleteForm.getPostId());
		postService.deletePost(user, post);
		return String.format("redirect:/app/home");
	}
//	@RequestMapping(method = RequestMethod.POST)
//	public String submitUpvotePost(@Valid @ModelAttribute("upvoteForm") PostForm postForm, BindingResult bindingResult, Model model) {
//		if (bindingResult.hasErrors()) {
//			return "upvoteForm";
//		}
//		try {
//			User existingUser = userAccountManagementService.getUserById(postForm.getUserId());
//			postService.upVotePost(existingUser, postService.getPostById(postForm.getExistingPostId()));
//
//		} catch (EntityNotFoundException e) {
//			model.addAttribute("error", "Need to login");
//			return getRedirectString(postForm.getUserId(), postForm.getExistingPostId());
//		} catch (Exception e) {
//			model.addAttribute("error", "Unexpected error while creating the post");
//			return getRedirectString(postForm.getUserId(), postForm.getExistingPostId());
//		}
//		return getRedirectString(postForm.getUserId(), postForm.getExistingPostId());
//	}

	public Comment createCommentInstance(User user, String content, Post post) {
		Comment comment = new Comment();
		comment.setUser(user);
		comment.setContent(content);
		comment.setPost(post);
		comment.setTime(new Date());
		return comment;
	}

	public String getRedirectString(long userId, long postId) {
		return String.format("redirect:post?userId=%s&postId=%s", userId, postId);
	}

}