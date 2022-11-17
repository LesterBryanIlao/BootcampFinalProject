package app.controller;

import java.util.Date;
import java.util.NoSuchElementException;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import app.base.service.PostService;
import app.base.service.UserSessionManagementService;
import app.bean.PostForm;
import app.entity.Post;
import app.entity.User;
import javassist.expr.NewArray;

@Controller
@RequestMapping("/postForm")
public class PostFormController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserSessionManagementService userSessionManagementService;

    private final static String POST_CREATE_ERROR = "Unexpected error occured while creating post";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(ModelMap modelMap) {
        System.out.println("here in get  post form");

        modelMap.addAttribute("postForm", new PostForm());
        return new ModelAndView("postForm");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult, Model model) {
        System.out.println("here in POST  post form");

        if (bindingResult.hasErrors()) {
            return "postForm";
        }
        
        
        Post post = createPostInstance(null, postForm.getContent());

        String existingPostIdString = postForm.getExistingPostId();
        if (!existingPostIdString.isEmpty()) {
            post.setId(Long.parseLong(existingPostIdString));
        }

        //try catch PostServiceImpl
        postService.createPost(null, post);

        return "postForm";
    }

    private Post createPostInstance(User user, String content) {
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);
        post.setUpvotes(0);
        post.setTime(new Date());
        return post;
    }

}
