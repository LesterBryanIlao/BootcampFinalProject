package app.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public void deleteByCommentId(long commentId);

	public void deleteByPostId(long postId);

	public void updateContent(long commentId, String newContent);

	public List<Comment> getByPostId(long postId, Pageable pageable);

}
