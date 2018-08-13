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
	

	@RequestMapping("/remove-comment-button")
	public String removeComment(String author, Long reviewId) {
		Comment toRemove = commentRepo.findByAuthor(author);
		commentRepo.delete(toRemove);
		
		return "redirect:/review?id=" + reviewId;
	}

	
}
