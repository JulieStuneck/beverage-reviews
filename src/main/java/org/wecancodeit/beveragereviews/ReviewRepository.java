package org.wecancodeit.beveragereviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByBeverageContains(Category beverage);
	//using getter .getBeverage
}
