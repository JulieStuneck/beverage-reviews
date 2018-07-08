package org.wecancodeit.beveragereviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)

public class ReviewControllerMockMVCTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@MockBean
	private TagRepository tagRepo;
	
	@Mock
	private Review review1;//only need to mock singles because need to confirm can find ID
	
	@Mock
	private Category category1;
	
	@Mock
	private Tag tag1;
	
		
	public void shouldRouteToSingleReviewView() throws Exception {
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review1));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("reviewTemplate")));
	}

	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long review1Id = 1;
		when(reviewRepo.findById(review1Id)).thenReturn(Optional.of(review1));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForSingleReview() throws Exception {
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldRouteToAllReviewView() throws Exception {
		mvc.perform(get("/reviews?")).andExpect(view().name(is("reviewsTemplate")));
		}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category1));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("categoryTemplate")));
	}
	
	@Test
	public void shouldBeOkForSingleCategory() throws Exception {
		long category1Id = 1;
		when(categoryRepo.findById(category1Id)).thenReturn(Optional.of(category1));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllCategoriesView() throws Exception {
		mvc.perform(get("/categories?")).andExpect(view().name(is("categoriesTemplate")));
		}
	
	@Test
	public void shouldBeOkForAllCategories() throws Exception {
		mvc.perform(get("/categories")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToSingleTagView() throws Exception {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(tag1));
		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tagTemplate")));
	}
	
	@Test
	public void shouldBeOkForSingleTag() throws Exception {
		long tag1Id = 1;
		when(tagRepo.findById(tag1Id)).thenReturn(Optional.of(tag1));
		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllTagsView() throws Exception {
		mvc.perform(get("/tags?")).andExpect(view().name(is("tagsTemplate")));
		}
	
	@Test
	public void shouldBeOkForAllTags() throws Exception {
		mvc.perform(get("/tags")).andExpect(status().isOk());
	}
	
}
