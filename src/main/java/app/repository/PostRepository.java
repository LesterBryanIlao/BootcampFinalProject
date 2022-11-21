package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	public static final String SELECT_ALL = "SELECT p.id, p.user.id, p.content, p.time, p.upvotes FROM Post p ";
	
	@Query(SELECT_ALL + "WHERE p.id = :post_id")
	public Post getByPostId(@Param("post_id") long postId);
	
	@Query(SELECT_ALL + "WHERE p.user.id = :user_id")
	public List<Post> getByUserId(@Param("user_id") long userId);
	
	@Modifying
	@Query("Delete FROM Post p where p.id = :post_id")
	public void deleteByPostId(@Param("post_id") long postId);
	
	@Modifying
	@Query("UPDATE Post p SET p.upvotes = :newUpvotes WHERE p.id = :post_id")
	public void updateUpvotes(@Param("post_id") long postId, @Param("newUpvotes") long newUpvotes);
	
	@Modifying
	@Query("UPDATE Post p SET p.content = :new_content WHERE p.id = :post_id")
	public void updateContent(@Param("post_id") long postid, @Param("new_content") String newContent);
}
