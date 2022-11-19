package app;

import org.hamcrest.core.AnyOf;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import app.entity.Post;
import app.entity.User;
import app.repository.PostRepository;
import app.repository.UserRepository;
import app.service.PostServiceImpl;

public class PostServiceImplTest {
   @Mock
    PostRepository postRepository = mock(PostRepository.class);
    
    @Mock
    UserRepository userRepository = mock(UserRepository.class);
    
    private PostServiceImpl postServiceImpl;
    private PostServiceImpl mockPostServiceImpl;
    private User user;
    private Post post;
    private Post newPost;
    private List<Post> posts;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        postServiceImpl = new PostServiceImpl();
        mockPostServiceImpl = mock(PostServiceImpl.class);
        Whitebox.setInternalState(postServiceImpl, "postRepository", postRepository);
        Whitebox.setInternalState(postServiceImpl, "userRepository", userRepository);
        
        user = new User();
        post = new Post();
        newPost = new Post();
        posts = new ArrayList<Post>();
    }
    
    @Test
    public void create_post_should_execute_once() {
        user.setId(1);

        post.setId(1);
        post.setUser(user);
        post.setContent("This should create post once");

       mockPostServiceImpl.createPost(user, post);
        verify(mockPostServiceImpl, times(1)).createPost(user, post);
   }
    
    @Test(expected = RuntimeException.class)
    public void create_post_for_non_existent_user_should_throw_runtime_exception() {
        post.setId(1);
        post.setUser(user);
        post.setContent("This should say user is not found");
        
        postServiceImpl.createPost(user, post);
    }

    @Test
    public void delete_post_should_execute_once() {
        user.setId(1);

        post.setId(1);
        post.setUser(user);
        post.setContent("This should create post");

        mockPostServiceImpl.deletePost(user, post);
        verify(mockPostServiceImpl, times(1)).deletePost(user, post);
    }
    
    @Test(expected = RuntimeException.class)
    public void delete_post_for_different_user_should_throw_runtime_exception() {
        user.setId(1);
        
        User anotherUser = new User();
        anotherUser.setId(2);
        
        post.setId(1);
        post.setUser(user);
        post.setContent("This should prevent another user from deleting this post");
        
        postServiceImpl.deletePost(anotherUser, post);
    }
    
    @Test(expected = RuntimeException.class)
    public void update_post_for_different_user_should_throw_runtime_exception() {
        user.setId(1);
        post.setId(1);
        post.setUser(user);
        
        User anotherUser = new User();
        anotherUser.setId(2);
        
        Post newPost = new Post();
        newPost.setId(1);
        newPost.setUser(anotherUser);
        
        post.setContent("This should prevent another user from updating this post");
        
        postServiceImpl.updatePostContent(post.getUser(), newPost);
    }
    
    @Test
    public void update_post_shoud_execute_once() {
        user.setId(1);

        post.setId(1);
        post.setUser(user);
        post.setContent("This should create post");

        mockPostServiceImpl.updatePostContent(user, post);
        verify(mockPostServiceImpl, times(1)).updatePostContent(user, post);
    }
    
    @Test
    public void upVotePost_should_save_new_value() {
        post.setId(1);
        post.setUpvotes(0);
        
        newPost.setId(1);
        newPost.setUpvotes(post.getUpvotes()+1);
        
        postRepository.updateUpvotes(post.getId(), newPost.getUpvotes());
        verify(postRepository, times(1)).updateUpvotes(post.getId(), newPost.getUpvotes());
    }
    
    @Test
    public void get_all_post_should_return_postList() {
        posts.add(post);
        posts.add(newPost);
        when(postRepository.findAll()).thenReturn(posts);
        assertTrue(posts.contains(post) && posts.contains(newPost));
        
    }
    
    @Test
    public void get_post_by_id_should_return_result() {
        post.setId(1);
        post.setContent("I want to be returned");
        
        postRepository.getByPostId(post.getId());
        verify(postRepository, times(1)).getByPostId(post.getId());
        
        when(postRepository.getByPostId(post.getId())).thenReturn(post);
        assertNotNull(post);
    }
    
}