package org.wecancodeit.beveragereviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity //The magic of JPA connects Category to Repository

public class Category {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String beverage; 
	
	@OneToMany(mappedBy="beverage") //connecting to ManyToOne in Review
	private Collection <Review> reviews;
	

	public Category(String beverage) {
		this.beverage = beverage;
	}
	
	
	public Category() {
		
	}
	
	public Collection<Review> getReview() {
		return reviews;
	}


	public long getId() {		
		return id;
	}

	public String getBeverage() {
		return beverage;
	}
	
	public Collection<Review> getReviews() {
		return reviews;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
}
