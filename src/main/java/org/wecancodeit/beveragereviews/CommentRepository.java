package org.wecancodeit.beveragereviews;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Comment findByAuthor(String commentAuthor);

}
