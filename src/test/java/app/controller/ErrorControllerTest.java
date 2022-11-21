package app.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;



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
