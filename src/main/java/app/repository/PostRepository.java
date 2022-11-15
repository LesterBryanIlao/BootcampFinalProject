package app.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

	public List<Post> getByUserId(long userId, Pageable pageable);

	public void deleteByPostId(long postId);

	public void updateUpvotes(long postId, long newUpvotes);

	public void updateContent(long postid, String newContent);

}
