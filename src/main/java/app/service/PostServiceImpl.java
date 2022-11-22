package app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.base.service.PostService;
import app.entity.Post;
import app.entity.User;
import app.repository.PostRepository;
import app.repository.UserRepository;
import app.util.sorter.ContentSorter;
import app.util.sorter.ContentSorterFactory;
import app.util.sorter.ContentType;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;
	
	@SuppressWarnings("unchecked")
	ContentSorter<Post> postSorter = (ContentSorter<Post>) ContentSorterFactory.instance().createSorter(ContentType.POST);

	@Transactional
	@Override
	public void createPost(User user, Post post) {
		final Optional<User> existingUser = userRepository.findById(user.getId());
		if (!existingUser.isPresent()) {
			throw new RuntimeException("User not found.");
		}
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
		List<Post> postsList = postRepository.findAll();
		Collections.sort(postsList, postSorter.getByTimeDescendingOrder());
		return postsList;
	}

	@Override
	public List<Post> getUserPosts(User user) {
		final Optional<User> existingUser = userRepository.findById(user.getId());
		if (!existingUser.isPresent()) {
			throw new RuntimeException("User not found.");
		}
		List<Post> postsList = postRepository.getByUserId(user.getId());
		Collections.sort(postsList, postSorter.getByTimeDescendingOrder());
		
		System.out.println(postsList.size());
		return postsList;
	}

	@Override
	public Post getPostById(long postId) {
		return postRepository.findById(postId).orElse(null);
	}
}