package org.wecancodeit.beveragereviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	private long id;

	private String description;

	@ManyToMany(mappedBy = "tags")//We want to track reviews by tags
	private Collection<Review> reviews;

	

	public Tag() {
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Collection<Review> getReviews() {

		return reviews;
	}
	
	public Tag(String description) {
		this.description = description;
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
