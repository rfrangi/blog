package bary.tech.test.blog.service;

import bary.tech.test.blog.model.Post;
import bary.tech.test.blog.model.dto.PostDto;
import bary.tech.test.blog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class PostServiceTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostService postService;

	@Test
	public void shouldReturnCreatedPost() {
		Post post = new Post();
		post.setTitle("Test title");
		post.setContent("Test content");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		post.setCreationDate(creationDate);
		postRepository.save(post);

		PostDto postDto = postService.getPost(post.getId());

		assertNotNull("Post shouldn't be null", postDto);
		assertEquals(postDto.content(), "Test content");
		assertEquals(postDto.title(), "Test title");
		assertEquals(postDto.creationDate(), creationDate);
	}

	@Test
	public void shouldReturnNullForNotExistingPost() {
		PostDto postDto = postService.getPost(123L);
		assertNull(postDto);
	}
}
