package app.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	public List<Post> getByUserId(long userId, Pageable pageable);

	@Query("Delete FROM RedditUserPosts rp where rp.post_id = :post_id")
	public void deleteByPostId(@Param("post_id") long postId);

	@Query("UPDATE RedditUserPosts rp SET rp.up_votes = rp.up_votes + :newUpvotes WHERE rp.post_id = :post_id")
	public void updateUpvotes(@Param("post_id") long postId, @Param("newUpvotes") long newUpvotes);

	@Query("UPDATE RedditUserPosts rp SET rp.post_content = :new_content WHERE rp.post_id = :post_id")
	public void updateContent(@Param("post_id") long postid, @Param("new_content") String newContent);

}
