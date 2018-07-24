package org.wecancodeit.beveragereviews;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

//	Comment findByDescription(String commentName);

}
