package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public List<Comment> getByPostId(long postId);
	
	@Query("DELETE FROM RedditPostComments rpc WHERE rpc.comment_id = :comment_id")
	public void deleteByCommentId(@Param("comment_id") long commentId);

	@Query("DELETE FROM RedditPostComments rpc WHERE rpc.post_id = :post_id")
	public void deleteByPostId(@Param("post_id") long postId);

	@Query("UPDATE RedditPostComments rpc SET rpc.comment_content = :new_content WHERE rpc.comment_id = :comment_id")
	public void updateContent(long commentId, String newContent);

}
