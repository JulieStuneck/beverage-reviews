package org.wecancodeit.beveragereviews;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByDescriptionIgnoreCase(String tagName);//query your tags to see if they exist

}
