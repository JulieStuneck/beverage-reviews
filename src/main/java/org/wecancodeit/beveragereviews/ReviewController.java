package org.wecancodeit.beveragereviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	CategoryRepository categoryRepo;

	@Resource
	TagRepository tagRepo;

	@RequestMapping("/review")
	public String find1Review(@RequestParam(value = "id") long reviewId, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(reviewId);

		if (review.isPresent()) {
			model.addAttribute("reviewsModel", review.get());
			return "reviewTemplate";
		}
		throw new ReviewNotFoundException();
	}

	@RequestMapping("/category")
	public String find1Category(@RequestParam(value = "id") long categoryId, Model model)
			throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(categoryId);

		if (category.isPresent()) {
			model.addAttribute("categoriesModel", category.get());
			return "categoryTemplate";
		}
		throw new CategoryNotFoundException();
	}

	@RequestMapping("/tag")
	public String find1Tag(@RequestParam(value = "id") long tag1Id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(tag1Id);

		if (tag.isPresent()) {
			model.addAttribute("tagsModel", tag.get());
			return "tagTemplate";
		}
		throw new TagNotFoundException();

	}

	@RequestMapping("/show-reviews") // name of endpoint
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());// name of model in thymeleaf
		return ("reviews");// name of template
	}

	@RequestMapping("/show-categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return ("categories");		
	}
	
	@RequestMapping("/show-tags") 
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return ("tags");
	}

}
