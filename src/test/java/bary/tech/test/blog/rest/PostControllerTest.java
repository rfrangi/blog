package bary.tech.test.blog.rest;

import bary.tech.test.blog.model.dto.PostDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest extends AbstractControllerTest {

	@Test
	public void shouldReturnFoundPost() throws Exception {
		// given
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		PostDto post = new PostDto("Title", "content", creationDate);

		// when
		when(postService.getPost(1L)).thenReturn(post);

		// then
		mockMvc.perform(get("/posts/1").accept(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.title", is("Title")))
			.andExpect(jsonPath("$.content", is("content")))
			.andExpect(jsonPath("$.creationDate", is(creationDate.toString())));

	}
}
