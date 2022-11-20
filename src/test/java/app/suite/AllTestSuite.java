package app.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import app.controller.ErrorControllerTest;
import app.controller.HomeControllerTest;
import app.controller.PostControllerTest;
import app.controller.PostFormControllerTest;
import app.service.CommentServiceImplTest;
import app.service.PostServiceImplTest;
import app.util.sorter.ContentSorterTest;

@RunWith(Suite.class)
@SuiteClasses({ HomeControllerTest.class, PostFormControllerTest.class, CommentServiceImplTest.class,
		PostServiceImplTest.class, ContentSorterTest.class, PostControllerTest.class, ErrorControllerTest.class })
public class AllTestSuite {

}
