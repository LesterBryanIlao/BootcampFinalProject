package app.controller;

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

import app.base.bean.CommentForm;
import javassist.expr.NewArray;

@Controller
@RequestMapping("/comments")
public class CommentController {
    
   /* @Autowired
    private UserCommentService userCommenetService;
    
    */
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(ModelMap map) {
      /*map.addAttribute("commentForm", new CommentForm());
        map.addAttribute("comments", userCommentService.getAllCommentsInPost());
        */
        return new ModelAndView("commentForm");
        
    }
    
    public String submitCommentFormString(@Valid @ModelAttribute("commentForm") CommentForm commentForm, BindingResult bindingResiltBindingResult, Model model)
    if(bindingResiltBindingResult.hasErrors()) {
        return "commentForm";
    }
    try {
        userCommentService.newComment(commentForm.getUserId(), commentForm.getCommentContent());
    }catch (Exception e) {
        model.addAttribute("useError", e.getMessage());
        return "commentForm";
        
    }
    
    return "redirect:comments";

}
