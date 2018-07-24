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
public class CommentController {

	@Resource
	private CommentRepository commentRepo;

	@Resource
	private ReviewRepository reviewRepo;

	// Constructor for Comment Entity calls for author, review & content. Extra step
	// needed here to tie the review to an Id.
	// Review will not be typed into form, will come in naturally with id
	@RequestMapping("/add-comment")
	public String addComment(String author, Long reviewId, String content) {// need to have exact matches with variables
																			// in HTMLForm
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		// create comment on review, using parameters from addComment(just above). The
		// Review Object was created immediately above (.get)
		Comment newComment = new Comment(author, review, content);
		commentRepo.save(newComment);

		return "redirect:/review?id=" + reviewId;
	}
	
//	@RequestMapping("/remove-comment-button")
//	public String removeCommentButton(@RequestParam String author, @RequestParam Long commentId, @RequestParam Long reviewId) {
//		Optional<Comment> commentToRemoveResult = commentRepo.findByAuthor(commentAuthor);
//		Comment commentToRemove = commentToRemoveResult.get();
//		
//		
////		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
////		Review review = reviewResult.get();
//		
//		commentRepo.delete(commentToRemove);
//		
//		return "redirect:/review?id=" + reviewId;
//	}
	
	
	//Use Ajax to remove Tag from Database (detach them from the reviews)
//	@RequestMapping(path = "/tags/remove/{tagName}", method = RequestMethod.POST)
//	public String RemoveTag(@PathVariable String tagName, Model model) {//only need a PathVariable for tag by name
//		
//		Tag tagToDelete = tagRepo.findByDescriptionIgnoreCase(tagName);//find tag that we want to delete
//		if(tagRepo.findByDescriptionIgnoreCase(tagName)!= null) {
//			for(Review review: tagToDelete.getReviews()) {//iterate over all reviews and remove the specific tag from each review (otherwise will remove all the reviews)
//				review.removeTag(tagToDelete);//tag entity has a collection of reviews. Hit on all reviews and see which have the tag. Every time find tag, remove it.
//				reviewRepo.save(review);//remove the tag from the review
//			}
//		}
//		tagRepo.delete(tagToDelete);//delete the tag from the whole database - we can do this because reviews are not required to have a tag
//		model.addAttribute("tagsModel", tagRepo.findAll());						
//		return "partials/tags-list-removed";
//	}
	
	
	//Show Comments with Java and Thymeleaf
//	@RequestMapping("/all-comments-ajax")
//	public String showAllComments(Model model) {
//		model.addAttribute("commentsModel", commentRepo.findAll());
//		return "commentsAjax";
//	}
	
	//Use Ajax to Add Comments to the Database
//	@RequestMapping(path = "/comments/{commentName}", method = RequestMethod.POST)
//	public String AddComment(@PathVariable String commentName, Model model) {
//		Comment commentToAdd=commentRepo.findByDescription(commentName);
//		if(commentToAdd == null) {
//			commentToAdd = new Comment(commentName);
//			commentRepo.save(commentToAdd);
//		}
//		
//		return "";
//	}

}
