package org.wecancodeit.beveragereviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
	
	@Mock
	private Review review1;
	
	@Mock
	private Category category1;
	
	@Test //also tag and category
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
	
	
//	public void shouldRouteToAllReviewView() throws Exception {
//			
//		}
//		
//	public void shouldBeOkForSingleReview() throws Exception {
//	}
//	
//	public void shouldBeOkForAllReviews() throws Exception {
//	}
}
