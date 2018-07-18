package org.wecancodeit.beveragereviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TagController {
	
	@Resource
	TagRepository tagRepo;
	
	@Resource
	ReviewRepository reviewRepo;
	
	//Can add tag with HTML forms
	@RequestMapping("/add-tag")//type in name of tag, then see if it exists already (String tagName referenced in HTML Form)
	public String addTag(@RequestParam(value="reviewId") Long reviewId, String tagName){//this id for review (not tag)
		Tag newTag = tagRepo.findByDescriptionIgnoreCase(tagName);//"Description" based on constructor in Tag.java
		if(newTag==null) {
			newTag = new Tag(tagName);
			tagRepo.save(newTag);
		}
		
		//checking on review - referencing the id from above
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		Tag existing = tagRepo.findByDescriptionIgnoreCase(tagName);
		if(!review.getTags().contains(existing)) {//if collection of tags for this particular review does not contain "existing", then
			review.addTag(newTag);//add tag to review
			reviewRepo.save(review);//update the information for the review
		}
		//redirect to the particular review page we are on
		return "redirect:/review?id=" + reviewId;
		
	}
	
	//Can Remove Tag with HTML Forms
	@RequestMapping("/remove-tag-button")
	public String removeTagButton(@RequestParam Long tagId, @RequestParam Long reviewId) {//Do need to know specific tag id as well as review id 
		Optional<Tag> tagToRemoveResult = tagRepo.findById(tagId);
		Tag tagToRemove = tagToRemoveResult.get();
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		
		review.removeTag(tagToRemove);
		reviewRepo.save(review);
		
		return "redirect:/review?id=" + reviewId;
	}
	
}
