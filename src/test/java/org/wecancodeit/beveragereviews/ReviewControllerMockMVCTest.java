package org.wecancodeit.beveragereviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class ReviewControllerMockMVCTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@Mock
	private Review review1;
	
	@Test //also tag and category
	public void shouldRouteToSingleReviewView() throws Exception {
		long review1Id = 1;
		when(reviewRepo.findById(review1Id)).thenReturn(Optional.of(review1));
		mvc.perform(get("/review1?id=1")).andExpect(view().name(is("review")));
	}

	
	public void shouldRouteToAllReviewView() throws Exception {
			
		}
		
	public void shouldBeOkForSingleReview() throws Exception {
	}
	
	public void shouldBeOkForAllReviews() throws Exception {
	}
}
