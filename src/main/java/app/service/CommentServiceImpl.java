package app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import app.base.service.CommentService;
import app.entity.Comment;
import app.entity.Post;
import app.entity.User;
import app.repository.CommentRepository;
import app.repository.PostRepository;

public class CommentServiceImpl implements CommentService{
    
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    
    @Override
    public void createComment(Post post, Comment comment, String content) {
        final Optional<Post> existingPost = postRepository.findById(post.getId());
        if(!existingPost.isPresent()) {
            throw new RuntimeException("Post not found.");
        }
        
        comment = new Comment();
        comment.setPost(existingPost.get());
        comment.setContent(content);
        comment.setTime(new Date());
        
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.deleteById(comment.getId());
    }

    @Override
    public void deletePostComments(Post post) {
        commentRepository.deleteByPostId(post.getId());
        
    }

    @Override
    public void updateComment(Comment comment, Comment newComment) {
        commentRepository.updateContent(comment.getId(), newComment.getContent());
        
    }

    @Override
    public List<Comment> getCommentFromPost(Post post) {
        post = new Post();
        return  commentRepository.getByPostId(post.getId(), null);

    }
    
}
