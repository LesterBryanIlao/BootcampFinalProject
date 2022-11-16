package app.base.service;

import java.util.List;

import app.entity.Post;
import app.entity.User;

public interface PostService  {

	public void createPost(User user, Post post);

	public void deletePost(User user, Post post);

	public void updatePostContent(User user, Post newPost);
	
	public void upVotePost(User user, Post post);
	
	public List<Post> getPosts();

	public List<Post> getUserPosts(User user);
	
	public Post getPostById(long postId);

}
