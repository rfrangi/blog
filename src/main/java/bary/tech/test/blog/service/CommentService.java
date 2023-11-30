package bary.tech.test.blog.service;

import bary.tech.test.blog.exceptions.ResourceNotFoundException;
import bary.tech.test.blog.model.Comment;
import bary.tech.test.blog.model.Post;
import bary.tech.test.blog.model.dto.CommentDto;
import bary.tech.test.blog.model.dto.NewCommentDto;
import bary.tech.test.blog.repository.PostRepository;
import bary.tech.test.blog.rest.CommentRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		return this.commentRepository.findByPostIdOrderByCreationDateDesc(postId)
				.orElse(new ArrayList<>())
				.stream()
				.map(it -> new CommentDto(it.getId(), it.getComment(), it.getAuthor(), it.getCreationDate()))
				.collect(Collectors.toList());
	}

	/**
	 * Creates a new comment
	 *
	 * @param postId id of the post
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if postId is null or there is no blog post for passed postId
	 */
	public Long addComment(Long postId, NewCommentDto newCommentDto) {
		final Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("{0} couldn't be found", postId)));

		//TODO: use MapStruct dep for convert dto to entity
		final Comment comment = new Comment();
		comment.setComment(newCommentDto.content());
		comment.setAuthor(newCommentDto.author());
		comment.setCreationDate(LocalDateTime.now());
		comment.setPost(post);
		
		return this.commentRepository.saveAndFlush(comment).getId();
	}
}
