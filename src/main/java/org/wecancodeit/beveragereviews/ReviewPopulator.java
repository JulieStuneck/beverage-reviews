package org.wecancodeit.beveragereviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ReviewPopulator implements CommandLineRunner {
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Override 
	public void run(String... args) throws Exception {
		
		Tag hot = new Tag("Hot");
		hot = tagRepo.save(hot);
		Tag cold = new Tag("Cold");
		cold = tagRepo.save(cold);
		Tag nonAlcoholic = new Tag("Non-Alcoholic");
		nonAlcoholic = tagRepo.save(nonAlcoholic);
		Tag alcoholic = new Tag("Alcoholic");
		alcoholic = tagRepo.save(alcoholic);
		
		Category coffee = new Category("Coffee");
		coffee = categoryRepo.save(coffee);
		Category tea = new Category("Tea");
		tea = categoryRepo.save(tea);
		Category beer = new Category("Beer");
		beer = categoryRepo.save(beer);
		Category wine = new Category("Wine");
		wine = categoryRepo.save(wine);
		
		//@Lob - add this to Review.java if want to add long description
		//Review(String name, String description, String image, Category beverage, Tag...tags)
		
		Review dunkin = new Review("Dunkin", "Loved by many.", "/images/Dunkin.jpg", coffee, hot, cold,nonAlcoholic);
		dunkin = reviewRepo.save(dunkin);
		reviewRepo.save(new Review("Starbucks", "Easy to find. A little too hot.", "/images/Starbucks.jpg", coffee, hot, cold, nonAlcoholic));
		reviewRepo.save(new Review("Mango Tea", "The Best - hot or iced.", "/images/MangoTea.jpg", tea, hot, cold, nonAlcoholic));
		reviewRepo.save(new Review("Constant Comment", "A necessity for any cupboard", "/images/BlackTea.jpg", tea, hot, cold, nonAlcoholic  ));
		reviewRepo.save(new Review("Hoppin' Frog", "Great beer! Worth the drive!", "/images/DarkBeer.jpg", beer, cold, alcoholic));
		reviewRepo.save(new Review("Homebrew", "A bit of a dice-toss, but great fun.", "/images/Homebrew.jpg", beer, cold, alcoholic));
		reviewRepo.save(new Review("White Wine", "Best served cold.", "WhiteWine.jpg", wine, cold, alcoholic));
		reviewRepo.save(new Review("Red Wine", "Great for sipping or with food.", "images/Cabernet.jpg", wine, alcoholic));
	}
	
	

}
