package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.base.service.PostService;
import app.entity.Post;
import app.entity.User;
import app.repository.PostRepository;
import app.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@Transactional
	@Override
	public void createPost(User user, Post post) {
	    System.out.println("here in create post");
//		// TODO Auto-generated method stub
//		final Optional<User> existingUser = userRepository.findById(user.getId());
//		if (!existingUser.isPresent()) {
//			throw new RuntimeException("User not found.");
//		}
		postRepository.save(post);
	}

	@Transactional
	@Override
	public void deletePost(User user, Post post) {
		if (!(post.getUser().getId() == user.getId())) {
			throw new RuntimeException("Current user not allowed to delete the post.");
		}
		postRepository.delete(post);
	}

	@Transactional
	@Override
	public void updatePostContent(User user, Post newPost) {

		if (!(newPost.getUser().getId() == user.getId())) {
			throw new RuntimeException("Current user not allowed to update the post.");
		}
		postRepository.save(newPost);
	}

	@Transactional
	@Override
	public void upVotePost(User user, Post post) {
		postRepository.updateUpvotes(post.getId(), post.getUpvotes() + 1);

	}

	@Override
	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	@Override
	public List<Post> getUserPosts(User user) {
		final Optional<User> existingUser = userRepository.findById(user.getId());
		if (!existingUser.isPresent()) {
			throw new RuntimeException("User not found.");
		}
		return postRepository.getByUserId(user.getId());
	}

	@Override
	public Post getPostById(long postId) {
		return postRepository.findById(postId).get();
	}
}
