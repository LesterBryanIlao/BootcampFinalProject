package app.controller;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;

import app.base.service.CommentService;
import app.base.service.PostService;
import app.base.service.UserSessionManagementService;
import app.bean.CommentForm;
import app.bean.PostForm;
import app.entity.Comment;
import app.entity.Post;
import app.entity.User;

public class ErrorControllerTest {
  
    
    private ErrorController errorController;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        errorController = new ErrorController();
       
        
    }
    
    @Test
    public void error_should_return_string_when_invoked() {
        ModelMap modelMap = new ModelMap();
        String error = "This is an error message for testing";
        errorController.showError(error, modelMap);
        assertTrue(modelMap.get("error").equals(error));
        
    }
}
