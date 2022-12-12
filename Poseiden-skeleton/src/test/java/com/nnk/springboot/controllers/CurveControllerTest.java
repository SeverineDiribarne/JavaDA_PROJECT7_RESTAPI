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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@EnableWebMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)

class CurveControllerTest {

	@InjectMocks
	private CurveController curvePointController;

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

	private BindingResult resultTest = new  BeanPropertyBindingResult(curvePointController, "curvePointForTest");
	private Model modelTest = new ConcurrentModel();


	private static CurvePoint curvePointForMock() {
		CurvePoint curvePoint4 = new CurvePoint();
		curvePoint4.setId(4);
		curvePoint4.setCurveId(4);
		curvePoint4.setTerm(4);
		curvePoint4.setValue(4);
		return curvePoint4;
	}

	private List<CurvePoint> listForMock() {

		CurvePoint curvePoint1 = new CurvePoint();
		curvePoint1.setId(1);
		curvePoint1.setCurveId(1);
		curvePoint1.setTerm(1);
		curvePoint1.setValue(1);

		CurvePoint curvePoint2 = new CurvePoint();
		curvePoint2.setId(2);
		curvePoint2.setCurveId(2);
		curvePoint2.setTerm(2);
		curvePoint2.setValue(2);

		CurvePoint curvePoint3 = new CurvePoint();
		curvePoint3.setId(3);
		curvePoint3.setCurveId(3);
		curvePoint3.setTerm(3);
		curvePoint3.setValue(3);

		List<CurvePoint> mockedList = new ArrayList<>();
		mockedList.add(curvePoint1);
		mockedList.add(curvePoint2);
		mockedList.add(curvePoint3);

		return mockedList;
	}
	//Functional test shouldGetCurvePoint
	@Test
	void testShouldGetCurvePoint() throws Exception{
		//given
		when(curvePointService.getCurvePoints()).thenReturn(listForMock());

		//when
		String urlResult = curvePointController.home(modelTest);
		Object attribute =  modelTest.getAttribute("curvePoints");

		//then
		Iterable<?> curvePointResults = null;
		if(attribute instanceof Iterable<?>) {
			curvePointResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of curvePointResult");
		}
		Iterator<?> curvePointResultsIterator = curvePointResults.iterator();
		Iterator<CurvePoint> curvePointExpectedIterator = listForMock().iterator();

		while(curvePointResultsIterator.hasNext()){

			CurvePoint curvePointResult = (CurvePoint) curvePointResultsIterator.next();
			CurvePoint curvePointExpected =  curvePointExpectedIterator.next();

			Assertions.assertEquals(curvePointResult.getId(), curvePointExpected.getId());
			Assertions.assertEquals(curvePointResult.getCurveId(), curvePointExpected.getCurveId());
			Assertions.assertEquals(curvePointResult.getTerm(), curvePointExpected.getTerm());
			Assertions.assertEquals(curvePointResult.getValue(),(curvePointExpected.getValue()));
		}
		Assertions.assertEquals(0, urlResult.compareTo("curvePoint/list"));
	}

	//EndPoint Test
	@Test
	void testShouldGetCurvePointForEndPoint() throws Exception{
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/curvePoint/list")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/list"));
	}


	static class AddCurvePointFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			CurvePoint curvePoint4 = curvePointForMock();
			return Stream.of(Arguments.of(curvePoint4));
		}
	}
	//Functional Test shouldAddCurvePointForm
	@ParameterizedTest
	@ArgumentsSource(AddCurvePointFormArgumentsProvider.class)
	void testShouldAddCurvePointForm(CurvePoint curvePointForTest) throws Exception{
		//given
		String urlResult = curvePointController.addCurvePointForm(curvePointForTest, modelTest);

		Object attribute =  modelTest.getAttribute("curvePoint");

		//then
		CurvePoint curvePointResult = null;
		if(attribute instanceof CurvePoint) {
			curvePointResult = (CurvePoint) attribute;
		}
		else {
			Assertions.fail("Bad type of curvePointResult");
		}

		Assertions.assertEquals(curvePointResult.getId(), curvePointForTest.getId());
		Assertions.assertEquals(curvePointResult.getCurveId(), curvePointForTest.getCurveId());
		Assertions.assertEquals(curvePointResult.getTerm(), curvePointForTest.getTerm());
		Assertions.assertEquals(curvePointResult.getValue(), curvePointForTest.getValue());
	

		Assertions.assertEquals(0, urlResult.compareTo("curvePoint/add"));	
	}

	//EndPoint Test
	@ParameterizedTest
	@ArgumentsSource(AddCurvePointFormArgumentsProvider.class)
	void testShouldAddCurvePointFormForEndPoint(CurvePoint curvePointForTest) throws Exception{
		//given
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/curvePoint/add")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/add"));
	}

	//Functional Test ShouldValidateCurvePoint
	@ParameterizedTest
	@ArgumentsSource(AddCurvePointFormArgumentsProvider.class)
	void testShouldValidateCurvePoint(CurvePoint curvePointForTest) throws Exception{
		//given
		//when
		ArgumentCaptor<CurvePoint> capturedCurvePointWhenSaveMethodIsCalled = ArgumentCaptor.forClass(CurvePoint.class);
		when(curvePointService.saveCurvePoint(capturedCurvePointWhenSaveMethodIsCalled.capture())).thenReturn(new CurvePoint());
		String urlResult = curvePointController.validate(curvePointForTest, resultTest, modelTest);
		Object attribute =  modelTest.getAttribute("curvePoints");

		//then
		CurvePoint curvePointResult = null;
		if(attribute instanceof CurvePoint) {
			curvePointResult = (CurvePoint) attribute;
		}
		else {
			Assertions.fail("Bad type of curvePointResult");
		}
		CurvePoint curvePointExpected = curvePointForTest;

		Assertions.assertEquals(curvePointResult.getId(), curvePointExpected.getId());
		Assertions.assertEquals(curvePointResult.getCurveId(), curvePointExpected.getCurveId());
		Assertions.assertEquals(curvePointResult.getTerm(), curvePointExpected.getTerm());
		Assertions.assertEquals(curvePointResult.getValue(), curvePointExpected.getValue());
		Assertions.assertEquals(0, urlResult.compareTo("redirect:/curvePoint/list"));

		CurvePoint capturedCurvePoint = capturedCurvePointWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedCurvePoint.getId());
		Assertions.assertEquals(4, capturedCurvePoint.getCurveId());
		Assertions.assertEquals(4, capturedCurvePoint.getTerm());
		Assertions.assertEquals(4, capturedCurvePoint.getValue());
	}

	private static CurvePoint emptyCurveIdCurvePointForMock() {
		CurvePoint curvePoint1 = new CurvePoint();
		curvePoint1.setId(1);
		curvePoint1.setCurveId(0);
		curvePoint1.setTerm(1);
		curvePoint1.setValue(1);

		return curvePoint1;
	}
	static class EmptyCurveIdCurvePointFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			CurvePoint curvePoint1 = emptyCurveIdCurvePointForMock();
			return Stream.of(Arguments.of(curvePoint1));
		}
	}

	//Functional Test ShouldValidateCurvePointEmptyCurveId
	@ParameterizedTest
	@ArgumentsSource(EmptyCurveIdCurvePointFormArgumentsProvider.class)
	void testShouldValidateCurvePointEmptyCurveId(CurvePoint bidForTest) throws Exception{
		//given
		//when
		String urlResult = curvePointController.validate(bidForTest, resultTest, modelTest);
		//then
		String attributeKey = "msgCurveId";
		Object errorMessageReturned = modelTest.getAttribute(attributeKey);

		String errorMessageResult = null;
		if(errorMessageReturned instanceof String) {
			errorMessageResult = (String) errorMessageReturned;
		}
		else {
			Assertions.fail("Bad type of errorMessageResult");
		}

		Assertions.assertEquals(0, urlResult.compareTo("curvePoint/add"));
		Assertions.assertEquals(0, errorMessageResult.compareTo("Your curveId number is equals to 0"));
	}
	
	private static CurvePoint emptyTermCurvePointForMock() {
		CurvePoint curvePoint1 = new CurvePoint();
		curvePoint1.setId(1);
		curvePoint1.setCurveId(1);
		curvePoint1.setTerm(0);
		curvePoint1.setValue(1);

		return curvePoint1;
	}
	static class EmptyTermCurvePointFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			CurvePoint curvePoint1 = emptyTermCurvePointForMock();
			return Stream.of(Arguments.of(curvePoint1));
		}
	}

	//Functional Test ShouldValidateCurvePointEmptyTerm
	@ParameterizedTest
	@ArgumentsSource(EmptyTermCurvePointFormArgumentsProvider.class)
	void testShouldValidateCurvePointEmptyTerm(CurvePoint bidForTest) throws Exception{
		//given
		//when
		String urlResult = curvePointController.validate(bidForTest, resultTest, modelTest);
		//then
		String attributeKey = "msgTerm";
		Object errorMessageReturned = modelTest.getAttribute(attributeKey);

		String errorMessageResult = null;
		if(errorMessageReturned instanceof String) {
			errorMessageResult = (String) errorMessageReturned;
		}
		else {
			Assertions.fail("Bad type of errorMessageResult");
		}

		Assertions.assertEquals(0, urlResult.compareTo("curvePoint/add"));
		Assertions.assertEquals(0, errorMessageResult.compareTo("Your term number is equals to 0"));
	}
	
	private static CurvePoint emptyValueCurvePointForMock() {
		CurvePoint curvePoint1 = new CurvePoint();
		curvePoint1.setId(1);
		curvePoint1.setCurveId(1);
		curvePoint1.setTerm(1);
		curvePoint1.setValue(0);

		return curvePoint1;
	}
	static class EmptyValueCurvePointFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			CurvePoint curvePoint1 = emptyValueCurvePointForMock();
			return Stream.of(Arguments.of(curvePoint1));
		}
	}

	//Functional Test ShouldValidateCurvePointEmptyValue
	@ParameterizedTest
	@ArgumentsSource(EmptyValueCurvePointFormArgumentsProvider.class)
	void testShouldValidateCurvePointEmptyValue(CurvePoint bidForTest) throws Exception{
		//given
		//when
		String urlResult = curvePointController.validate(bidForTest, resultTest, modelTest);
		//then
		String attributeKey = "msgValue";
		Object errorMessageReturned = modelTest.getAttribute(attributeKey);

		String errorMessageResult = null;
		if(errorMessageReturned instanceof String) {
			errorMessageResult = (String) errorMessageReturned;
		}
		else {
			Assertions.fail("Bad type of errorMessageResult");
		}

		Assertions.assertEquals(0, urlResult.compareTo("curvePoint/add"));
		Assertions.assertEquals(0, errorMessageResult.compareTo("Your value number is equals to 0"));
	}
	
	//EndPoint Test
	@Test
	void testShouldValidateCurvePointForEndPoint() throws Exception{
		//given
		//When
		when(curvePointService.saveCurvePoint(any())).thenReturn(new CurvePoint());
		//then
		mockMvc.perform(
				post("/curvePoint/validate")
				.param("id","1")
				.param("curveId","1")
				.param("term","1")
				.param("value", "1")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))

		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/curvePoint/list"))	;
	}

	static class shouldShowUpdateFormArgumentsProvider implements ArgumentsProvider{
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
			CurvePoint curvePoint4 = curvePointForMock();
			return Stream.of(Arguments.of(curvePoint4));
		}
	}

	//Functional Test shouldShowUpdateForm
	@ParameterizedTest
	@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
	void testShouldShowUpdateForm() throws Exception{
		//given
		Integer id = 4;
		when(curvePointService.getCurvePointById(id)).thenReturn(Optional.of(curvePointForMock()));

		String urlResult = curvePointController.showUpdateForm( id,  modelTest);
		Object attribute =  modelTest.getAttribute("curvePoint");

		//then
		CurvePoint curvePointResult = null;
		if(attribute instanceof CurvePoint) {
			curvePointResult = (CurvePoint) attribute;
		}
		else {
			Assertions.fail("Bad type of curvePointResult");
		}

		Assertions.assertEquals(curvePointResult.getId(), curvePointForMock().getId());
		Assertions.assertEquals(curvePointResult.getCurveId(), curvePointForMock().getCurveId());
		Assertions.assertEquals(curvePointResult.getTerm(), curvePointForMock().getTerm());
		Assertions.assertEquals(curvePointResult.getValue(),curvePointForMock().getValue());
	
		Assertions.assertEquals(0, urlResult.compareTo("curvePoint/update"));	
	}

	//EndPoint Test
	@Test
	void testShouldShowUpdateFormForEndPoint() throws Exception {
		//given
		//When
		when(curvePointService.getCurvePointById(any())).thenReturn(Optional.of(curvePointForMock()));
		//Then	
		mockMvc.perform(MockMvcRequestBuilders
				.get("/curvePoint/update/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/update"));
	}

	//Functional test shouldUpdateBid
	@ParameterizedTest
	@ArgumentsSource(shouldShowUpdateFormArgumentsProvider.class)
	void testShouldUpdateCurvePoint(CurvePoint curvePointForTest) throws Exception{
		//given
		when(curvePointService.getCurvePointById(any())).thenReturn(Optional.of(curvePointForTest));
		curvePointForTest.setCurveId(5);
		curvePointForTest.setTerm(5);
		curvePointForTest.setValue(5);
		
		when(curvePointService.saveCurvePoint(any())).thenReturn(curvePointForTest);

		ArgumentCaptor<CurvePoint> capturedCurvePointWhenSaveMethodIsCalled = ArgumentCaptor.forClass(CurvePoint.class);
		when(curvePointService.saveCurvePoint(capturedCurvePointWhenSaveMethodIsCalled.capture())).thenReturn(new CurvePoint());
		
		String urlResult = curvePointController.updateCurvePoint(4, curvePointForTest, resultTest, modelTest);
		Object attribute =  modelTest.getAttribute("curvePoint");
		//then
		CurvePoint curvePointResult = null;
		if(attribute instanceof CurvePoint) {
			curvePointResult = (CurvePoint) attribute;
		}
		else {
			Assertions.fail("Bad type of curvePointResult");
		}

		Assertions.assertEquals(curvePointResult.getId(), curvePointForTest.getId());
		Assertions.assertEquals(curvePointResult.getCurveId(), curvePointForTest.getCurveId());
		Assertions.assertEquals(curvePointResult.getTerm(), curvePointForTest.getTerm());
		Assertions.assertEquals(curvePointResult.getValue(), curvePointForTest.getValue());
		
		Assertions.assertEquals(0, urlResult.compareTo("redirect:/curvePoint/list"));	
		
		CurvePoint capturedCurvePoint = capturedCurvePointWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedCurvePoint.getId());
		Assertions.assertEquals(5, capturedCurvePoint.getCurveId());
		Assertions.assertEquals(5, capturedCurvePoint.getTerm());
		Assertions.assertEquals(5, capturedCurvePoint.getValue());
	}
	
	//Functional Test ShouldUpdateCurvePointEmptyCurveId
		@ParameterizedTest
		@ArgumentsSource(EmptyCurveIdCurvePointFormArgumentsProvider.class)
		void testShouldUpdateCurvePointEmptyCurveId(CurvePoint bidForTest) throws Exception{
			//given
			//when
			String urlResult = curvePointController.updateCurvePoint(1, bidForTest, resultTest, modelTest);
			//then
			String attributeKey = "msgCurveId";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);

			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("curvePoint/update"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your curveId number is equals to 0"));
		}
		
		//Functional Test ShouldUpdateCurvePointEmptyTerm
		@ParameterizedTest
		@ArgumentsSource(EmptyTermCurvePointFormArgumentsProvider.class)
		void testShouldUpdateCurvePointEmptyTerm(CurvePoint bidForTest) throws Exception{
			//given
			//when
			String urlResult = curvePointController.updateCurvePoint(1, bidForTest, resultTest, modelTest);
			//then
			String attributeKey = "msgTerm";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);

			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("curvePoint/update"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your term number is equals to 0"));
		}
	
		//Functional Test ShouldUpdateCurvePointEmptyValue
		@ParameterizedTest
		@ArgumentsSource(EmptyValueCurvePointFormArgumentsProvider.class)
		void testShouldUpdateCurvePointEmptyValue(CurvePoint bidForTest) throws Exception{
			//given
			//when
			String urlResult = curvePointController.updateCurvePoint(1, bidForTest, resultTest, modelTest);
			//then
			String attributeKey = "msgValue";
			Object errorMessageReturned = modelTest.getAttribute(attributeKey);

			String errorMessageResult = null;
			if(errorMessageReturned instanceof String) {
				errorMessageResult = (String) errorMessageReturned;
			}
			else {
				Assertions.fail("Bad type of errorMessageResult");
			}

			Assertions.assertEquals(0, urlResult.compareTo("curvePoint/update"));
			Assertions.assertEquals(0, errorMessageResult.compareTo("Your value number is equals to 0"));
		}
		
	//EndPoint Test
	@Test
	void testShouldUpdateCurvePointForEndPoint() throws Exception {
		//given
		//when	
		when(curvePointService.getCurvePointById(any())).thenReturn(Optional.of(curvePointForMock()));
		when(curvePointService.saveCurvePoint(any())).thenReturn(new CurvePoint());
		//then
		mockMvc.perform(
				post("/curvePoint/update/{id}",4)
				.param("id", "4")
				.param("CurveId", "4")
				.param("Term", "4")
				.param("value", "4")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/curvePoint/list"));
	}
	
	private List<CurvePoint> listForMockDeleted() {

		CurvePoint curvePoint2 = new CurvePoint();
		curvePoint2.setId(2);
		curvePoint2.setCurveId(2);
		curvePoint2.setTerm(2);
		curvePoint2.setValue(2);
		

		CurvePoint curvePoint3 = new CurvePoint();
		curvePoint3.setId(3);
		curvePoint3.setCurveId(3);
		curvePoint3.setTerm(3);
		curvePoint3.setValue(3);
		
		List<CurvePoint> mockedList = new ArrayList<>();
		mockedList.add(curvePoint2);
		mockedList.add(curvePoint3);

		return mockedList;
	}
	//Functional Test shouldDeleteCurvePoint
	@Test
	void testShouldDeleteCurvePoint() throws Exception{
		//given
		Integer id =1;
		//when
		ArgumentCaptor<CurvePoint> capturedCurvePointWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(CurvePoint.class);
		Mockito.doNothing().when(curvePointService).deleteCurvePoint(capturedCurvePointWhenDeleteMethodIsCalled.capture());
		
		when(curvePointService.getCurvePointById(id)).thenReturn(Optional.of(listForMock().get(0)));
		when(curvePointService.getCurvePoints()).thenReturn(listForMockDeleted());
		String urlResult = curvePointController.deleteCurvePoint(id, modelTest);
		Object attribute =  modelTest.getAttribute("curvePointList");

		//then
		Iterable<?> curvePointResults = null;
		if(attribute instanceof Iterable<?>) {
			curvePointResults = (Iterable<?>) attribute;
		}
		else {
			Assertions.fail("Bad type of curvePointResult");
		}
		Iterator<?> curvePointResultsIterator = curvePointResults.iterator();
		Iterator<CurvePoint> curvePointExpectedIterator = listForMockDeleted().iterator();

		CurvePoint curvePointResult1 = (CurvePoint) curvePointResultsIterator.next();
		
		CurvePoint curvePointExpected1 =  curvePointExpectedIterator.next();
		Assertions.assertEquals(curvePointResult1.getId(), curvePointExpected1.getId());
		Assertions.assertEquals(curvePointResult1.getCurveId(), curvePointExpected1.getCurveId());
		Assertions.assertEquals(curvePointResult1.getTerm(), curvePointExpected1.getTerm());
		Assertions.assertEquals(curvePointResult1.getValue(),curvePointExpected1.getValue());
		

		CurvePoint curvePointResult2 = (CurvePoint) curvePointResultsIterator.next();
		CurvePoint curvePointExpected2 =  curvePointExpectedIterator.next();

		Assertions.assertEquals(curvePointResult2.getId(), curvePointExpected2.getId());
		Assertions.assertEquals(curvePointResult2.getCurveId(), curvePointExpected2.getCurveId());
		Assertions.assertEquals(curvePointResult2.getTerm(), curvePointExpected2.getTerm());
		Assertions.assertEquals(curvePointResult2.getValue(), curvePointExpected2.getValue());
	
		
		Assertions.assertEquals(0, urlResult.compareTo("redirect:/curvePoint/list"));	
		
		CurvePoint capturedCurvePoint= capturedCurvePointWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals(1, capturedCurvePoint.getId());
		Assertions.assertEquals(1, capturedCurvePoint.getCurveId());
		Assertions.assertEquals(1, capturedCurvePoint.getTerm());
		Assertions.assertEquals(1, capturedCurvePoint.getValue());
		
	}

	//EndPoint Test
	@Test
	void testShouldDeleteCurvePointForEndPoint() throws Exception {
		//given
		Integer id =1;
		//when
		when(curvePointService.getCurvePointById(id)).thenReturn(Optional.of(listForMock().get(1)));
		when(curvePointService.getCurvePoints()).thenReturn(listForMock());
		//then
		mockMvc.perform(MockMvcRequestBuilders
				.get("/curvePoint/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/curvePoint/list"));
	}
}