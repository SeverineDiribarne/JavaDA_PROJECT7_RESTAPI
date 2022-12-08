package com.nnk.springboot.controllers;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

	@InjectMocks
	private HomeController homeController;

	private Model modelTest = new ConcurrentModel();


	@Test
	void testShouldGetHome(){
		//when
		String urlResult = homeController.home(modelTest);

		//then
		Assertions.assertEquals(0, urlResult.compareTo("home"));
	}

	@Test
	void testShouldGetHom(){
		//when
		String urlResult = homeController.hom(modelTest);

		//then
		Assertions.assertEquals(0, urlResult.compareTo("home"));
	}
	@Test
	void testShouldGetAdminHome() {
		//when
		String urlResult = homeController.adminHome(modelTest);

		//then
		Assertions.assertEquals(0, urlResult.compareTo("redirect:/bidList/list"));	

	}
}
