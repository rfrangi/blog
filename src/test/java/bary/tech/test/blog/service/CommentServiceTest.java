package bary.tech.test.blog.service;

import bary.tech.test.blog.model.Post;
import bary.tech.test.blog.model.dto.CommentDto;
import bary.tech.test.blog.model.dto.NewCommentDto;
import bary.tech.test.blog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CommentServiceTest {

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentService commentService;

	@Test
	public void shouldAddComment() {
		Post post = createTestPost();

		NewCommentDto comment = new NewCommentDto("Author", "Content");
		Long commentId = commentService.addComment(post.getId(), comment);

		assertThat("Comment id shouldn't be null", commentId, notNullValue());
	}

	private Post createTestPost() {
		Post post = new Post();
		post.setTitle("Test title");
		post.setContent("Test content");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		post.setCreationDate(creationDate);
		return postRepository.save(post);
	}

	@Test
	public void shouldReturnAddedComment() {
		Post post = createTestPost();

		NewCommentDto comment = new NewCommentDto("Author", "Content");

		commentService.addComment(post.getId(), comment);

		List<CommentDto> comments = commentService.getCommentsForPost(post.getId());

		assertThat("There should be one comment", comments, hasSize(1));
		assertThat(comments.get(0).author(), equalTo("Author"));
		assertThat(comments.get(0).comment(), equalTo("Content"));
	}
}
