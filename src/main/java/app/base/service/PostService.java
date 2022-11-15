package app.base.service;

import java.util.List;

import app.entity.Post;
import app.entity.User;

public interface PostService  {

	public void createPost(User user, Post post);

	public void deletePost(Post post);

	public void updatePostContent(Post existingPost, Post newPost);
	
	public void upVotePost(User user);
	
	public List<Post> getPosts();

	public List<Post> getUserPosts(User user);
	
}
