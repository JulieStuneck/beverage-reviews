package org.wecancodeit.beveragereviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	Collection<Category> findByCategoryContains(Category category);
	
	Collection<Category> findByCategoryId(Long id);

}
