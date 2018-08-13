package org.wecancodeit.beveragereviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	//Show Tags with Java and Thymeleaf
	@RequestMapping("/all-tags-ajax")
	public String showAllTags(Model model) {
		model.addAttribute("tagsModel", tagRepo.findAll());
		return "tagsAjax";//name of html template
	}
	
	//Use Ajax to Add Tags to the Database
	@RequestMapping(path = "/tags/{tagName}", method = RequestMethod.POST)
	public String AddTag(@PathVariable String tagName, Model model) {
		Tag tagToAdd = tagRepo.findByDescriptionIgnoreCase(tagName);
		if(tagToAdd == null) {
			tagToAdd = new Tag(tagName);
			tagRepo.save(tagToAdd);
		}
		model.addAttribute("tagsModel", tagRepo.findAll());		
		return "partials/tags-list-added";//tags-list connects to class in Ajax file
	}

	
	//Because the review and tag are tied together, Ajax wants to delete the review with the tag (not tag alone)
	
	//Use Ajax to remove Tag from Database (detach them from the reviews)
	@RequestMapping(path = "/tags/remove/{tagName}", method = RequestMethod.POST)
	public String RemoveTag(@PathVariable String tagName, Model model) {//only need a PathVariable for tag by name
		
		Tag tagToDelete = tagRepo.findByDescriptionIgnoreCase(tagName);//find tag that we want to delete
		if(tagRepo.findByDescriptionIgnoreCase(tagName)!= null) {
			for(Review review: tagToDelete.getReviews()) {//iterate over all reviews and remove the specific tag from each review (otherwise will remove all the reviews)
				review.removeTag(tagToDelete);//tag entity has a collection of reviews. Hit on all reviews and see which have the tag. Every time find tag, remove it.
				reviewRepo.save(review);//remove the tag from the review
			}
		}
		tagRepo.delete(tagToDelete);//delete the tag from the whole database - we can do this because reviews are not required to have a tag
		model.addAttribute("tagsModel", tagRepo.findAll());						
		return "partials/tags-list-removed";
	}

}
