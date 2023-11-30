package bary.tech.test.blog.rest;

import bary.tech.test.blog.model.dto.CommentDto;
import bary.tech.test.blog.model.dto.NewCommentDto;
import bary.tech.test.blog.model.dto.PostDto;
import bary.tech.test.blog.service.CommentService;
import bary.tech.test.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;
	private final CommentService commentService;

	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}


	@PostMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addComment(@PathVariable Long id, @RequestBody NewCommentDto commentDto) {
		return this.commentService.addComment(id, commentDto);
	}

	@GetMapping(value = "/{id}/comments")
	public List<CommentDto> getCommentsForPost(@PathVariable Long id) {
		return this.commentService.getCommentsForPost(id);
	}
}
