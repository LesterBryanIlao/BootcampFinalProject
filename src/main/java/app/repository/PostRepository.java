package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	public static final String SELECT_ALL = "SELECT post_id, user_id, post_content, date_posted, up_votes FROM RedditUserPosts ";
	
	public List<Post> getByPostId(long postId);
	
	@Query(SELECT_ALL + "WHERE post_id = :post_id")
	public List<Post> findAllById(@Param("post_id")long postId);
	
	@Query(SELECT_ALL + "WHERE user_id = :user_id")
	public List<Post> getByUserId(@Param("user_id") long userId);
	
	@Query("Delete FROM RedditUserPosts rp where rp.post_id = :post_id")
	public void deleteByPostId(@Param("post_id") long postId);

	@Query("UPDATE RedditUserPosts rp SET rp.up_votes = rp.up_votes + :newUpvotes WHERE rp.post_id = :post_id")
	public void updateUpvotes(@Param("post_id") long postId, @Param("newUpvotes") long newUpvotes);

	@Query("UPDATE RedditUserPosts rp SET rp.post_content = :new_content WHERE rp.post_id = :post_id")
	public void updateContent(@Param("post_id") long postid, @Param("new_content") String newContent);
}
