package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public List<Comment> getByPostId(long postId);
	
	@Modifying
	@Query("DELETE FROM Comment c WHERE c.post.id = :post_id")
	public void deleteByPostId(@Param("post_id") long postId);

}
