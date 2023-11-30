package bary.tech.test.blog.rest;
import bary.tech.test.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findByPostIdOrderByCreationDateDesc(Long postId);
}
