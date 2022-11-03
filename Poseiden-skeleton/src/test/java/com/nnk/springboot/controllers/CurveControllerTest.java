package com.nnk.springboot.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@EnableWebMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)

class CurveControllerTest {

	@InjectMocks
	private CurveController curveController;


	private MockMvc mockMvc;

	@MockBean
	CurvePointService curvePointService;

	@Autowired 
	WebApplicationContext webApplicationContext;

	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)     //Configurer Mock MVC
				.build();
	}

	private Model modelTest = new ConcurrentModel();

	private static CurvePoint curveForMock() {
		CurvePoint curve4 = new CurvePoint();
		curve4.setId(4);
		curve4.setCurveId(4);
		curve4.setAsOfDate(null);
		curve4.setCreationDate(null);
		curve4.setTerm(0);
		curve4.setValue(12);
		return curve4;
	}

	private List<CurvePoint> listForMock() {

		CurvePoint curve1 = new CurvePoint();
		curve1.setId(1);
		curve1.setCurveId(1);
		curve1.setAsOfDate(null);
		curve1.setCreationDate(null);
		curve1.setTerm(0);
		curve1.setValue(15);


		CurvePoint curve2 = new CurvePoint();
		curve2.setId(1);
		curve2.setCurveId(1);
		curve2.setAsOfDate(null);
		curve2.setCreationDate(null);
		curve2.setTerm(0);
		curve2.setValue(15);


		CurvePoint curve3 = new CurvePoint();
		curve3.setId(1);
		curve3.setCurveId(1);
		curve3.setAsOfDate(null);
		curve3.setCreationDate(null);
		curve3.setTerm(0);
		curve3.setValue(15);

		List<CurvePoint> mockedList = new ArrayList<>();
		mockedList.add(curve1);
		mockedList.add(curve2);
		mockedList.add(curve3);

		return mockedList;
	}

	@Test
	void testShouldGetCurvePoint() throws Exception{
		//given
		when(curvePointService.getCurvePoints()).thenReturn(listForMock());

		//when
		String urlResult = curveController.home(modelTest);
		Object attribute =  modelTest.getAttribute("curvePoints");

		//then
		Iterable<?> curveResults = null;
		if(attribute instanceof Iterable<?>) {
			curveResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of curveResult");
		}

		mockMvc.perform(MockMvcRequestBuilders
				.get("/curvePoint/list")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/list"));
		
		Iterator<?> curveResultsIterator = curveResults.iterator();
		Iterator<CurvePoint> curveExpectedIterator = listForMock().iterator();

		while(curveResultsIterator.hasNext()){

			CurvePoint curveResult = (CurvePoint) curveResultsIterator.next();
			CurvePoint curveExpected =  curveExpectedIterator.next();

			Assertions.assertEquals(curveResult.getId(),curveExpected.getCurveId());
			Assertions.assertEquals(curveResult.getCurveId(),curveExpected.getCurveId());
			Assertions.assertEquals(curveResult.getTerm(), curveExpected.getTerm());
			Assertions.assertEquals(curveResult.getValue(),curveExpected.getValue());
		}
		Assertions.assertEquals(0, urlResult.compareTo("curvePoint/list"));
	}

	static class AddCurveFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			CurvePoint curve4 = curveForMock();
			return Stream.of(Arguments.of(curve4));
		}
	}

	@ParameterizedTest
	@ArgumentsSource(AddCurveFormArgumentsProvider.class)
	void testShouldAddCurveForm(CurvePoint curveForTest) throws Exception{
		//given
		String urlResult = curveController.addCurvePointForm(curveForTest, modelTest);

		Object attribute =  modelTest.getAttribute("curvePoint");

		//then
		CurvePoint curveResult = null;
		if(attribute instanceof CurvePoint) {
			curveResult = (CurvePoint) attribute;
		}
		else {
			Assertions.fail("Bad type of curveResult");
		}
		mockMvc.perform(MockMvcRequestBuilders
				.get("/curvePoint/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/add"));
		Assertions.assertEquals(curveResult.getId() , curveForTest.getId());
		Assertions.assertEquals(curveResult.getCurveId() , curveForTest.getCurveId());
		Assertions.assertEquals(curveResult.getTerm() , curveForTest.getTerm());
		Assertions.assertEquals(curveResult.getValue() , curveForTest.getValue());

		Assertions.assertEquals(0, urlResult.compareTo("curvePoint/add"));	
	}

	@Test
	void testShouldValidateCurvePoint() throws Exception{
		//given
		//When
		when(curvePointService.saveCurvePoint(any())).thenReturn(new CurvePoint());
		//then
		mockMvc.perform(
				post("/curvePoint/validate")
				.param("id","1")
				.param("term","10.0")
				.param("value","10.0")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/list"))
		;

	}

	@Test
	void testShouldShowUpdateForm() throws Exception {
		//given
		//When
		when(curvePointService.getCurvePointById(any())).thenReturn(Optional.of(curveForMock()));
		//Then	
		mockMvc.perform(MockMvcRequestBuilders
				.get("/curvePoint/update/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/update"));
	}

	@Test
	void testShouldUpdateCurve() throws Exception {
		//given
		//when	
		when(curvePointService.getCurvePointById(any())).thenReturn(Optional.of(curveForMock()));
		when(curvePointService.saveCurvePoint(any())).thenReturn(new CurvePoint());
		//then
		mockMvc.perform(
				post("/curvePoint/update/{id}",4)
				.param("id","1")
				.param("term","10.0")
				.param("value","10.0")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/curvePoint/list"));
	}



	@Test
	void testShouldDeleteCurvePoint() throws Exception {
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/curvePoint/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/curvePoint/list"));
	}
}
