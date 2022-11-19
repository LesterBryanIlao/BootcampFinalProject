package app.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import app.controller.HomeControllerTest;
import app.controller.PostFormControllerTest;
import app.service.CommentServiceImplTest;
import app.service.PostServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({ HomeControllerTest.class, PostFormControllerTest.class, CommentServiceImplTest.class,
		PostServiceImplTest.class })
public class AllTestSuite {

}
