package app.util.sorter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.entity.Comment;
import app.entity.Post;

public class ContentSorterTest {
	private List<Comment> comments = new ArrayList<>();
	private List<Post> posts = new ArrayList<>();
	private Calendar calendar = Calendar.getInstance();
	private CommentSorter commentsSorter = new CommentSorter();
	private PostSorter postSorter = new PostSorter();

	@Before
	public void setup() {
		comments.clear();
		posts.clear();
	}

	@Test
	public void comment_sorter_should_sort_comments_ascending() {

		Comment comment1 = createCommentWithYearSpecified(2020);
		Comment comment2 = createCommentWithYearSpecified(1999);
		Comment comment3 = createCommentWithYearSpecified(2500);
		Comment comment4 = createCommentWithYearSpecified(1998);

		comments.add(comment1);
		comments.add(comment2);
		comments.add(comment3);
		comments.add(comment4);

		Collections.sort(comments, commentsSorter.getByTimeAscendingOrder());

		assertTrue(comments.get(0).equals(comment4) && comments.get(1).equals(comment2)
				&& comments.get(2).equals(comment1) && comments.get(3).equals(comment3));

	}

	@Test
	public void comment_sorter_should_sort_comments_dscending() {
		Comment comment1 = createCommentWithYearSpecified(2020);
		Comment comment2 = createCommentWithYearSpecified(1999);
		Comment comment3 = createCommentWithYearSpecified(2500);

		comments.add(comment1);
		comments.add(comment2);
		comments.add(comment3);

		Collections.sort(comments, commentsSorter.getByTimeDescendingOrder());

		assertTrue(comments.get(0).equals(comment3) && comments.get(1).equals(comment1)
				&& comments.get(2).equals(comment2));
	}

	@Test
	public void post_sorter_should_sort_post_ascending() {

		Post post1 = createPostWithYearSpecified(2020);
		Post post2 = createPostWithYearSpecified(1999);
		Post post3 = createPostWithYearSpecified(2500);
		Post post4 = createPostWithYearSpecified(1998);

		posts.add(post1);
		posts.add(post2);
		posts.add(post3);
		posts.add(post4);

		Collections.sort(posts, postSorter.getByTimeAscendingOrder());

		assertTrue(posts.get(0).equals(post4) && posts.get(1).equals(post2) && posts.get(2).equals(post1)
				&& posts.get(3).equals(post3));

	}

	@Test
	public void post_sorter_should_sort_post_descending() {

		Post post1 = createPostWithYearSpecified(2020);
		Post post2 = createPostWithYearSpecified(1999);
		Post post3 = createPostWithYearSpecified(2500);
		Post post4 = createPostWithYearSpecified(1998);

		posts.add(post1);
		posts.add(post2);
		posts.add(post3);
		posts.add(post4);

		Collections.sort(posts, postSorter.getByTimeDescendingOrder());

		assertTrue(posts.get(0).equals(post3) && posts.get(1).equals(post1) && posts.get(2).equals(post2)
				&& posts.get(3).equals(post4));

	}

	@Test
	public void content_sorter_factory_should_return_the_appopriate_sorter() {
		ContentSorterFactory factory = ContentSorterFactory.instance();
		assertTrue(factory.createSorter(ContentType.POST) instanceof PostSorter);
		assertTrue(factory.createSorter(ContentType.COMMENT) instanceof CommentSorter);
	}

	private Comment createCommentWithYearSpecified(int year) {
		Comment comment = new Comment();
		calendar.set(Calendar.YEAR, year);
		comment.setTime(calendar.getTime());
		return comment;
	}

	private Post createPostWithYearSpecified(int year) {
		Post post = new Post();
		calendar.set(Calendar.YEAR, year);
		post.setTime(calendar.getTime());
		return post;
	}
}
