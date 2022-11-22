package com.nnk.springboot.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CurvePointServiceTest {

	private static final Integer CURVE_ID = 1;

	@Mock
	private CurvePointRepository curveRepository;

	@InjectMocks
	CurvePointService curvePointService = new CurvePointService();


	public List<CurvePoint> listForMock() {

		CurvePoint curve1 = new CurvePoint();
		curve1.setCurveId(1);
		curve1.setId(1);
		curve1.setTerm(1);
		curve1.setValue(1);

		CurvePoint curve2 = new CurvePoint();
		curve2.setCurveId(2);
		curve2.setId(2);
		curve2.setTerm(2);
		curve2.setValue(2);

		CurvePoint curve3 = new CurvePoint();
		curve3.setCurveId(3);
		curve3.setId(3);
		curve3.setTerm(3);
		curve3.setValue(3);

		List<CurvePoint> mockedList = new ArrayList<>();
		mockedList.add(curve1);
		mockedList.add(curve2);
		mockedList.add(curve3);
		return mockedList;
	}
	/**
	 * Get curvePoint tests
	 * @return all list of curvePoint
	 */
	@Test
	void testGetCurvePoint() {

		//Given
		when(curveRepository.findAll()).thenReturn(listForMock());

		//When
		Iterable<CurvePoint> curveResults = curvePointService.getCurvePoints();

		//Then
		Iterator<CurvePoint> curveResultsIterator = curveResults.iterator();
		Iterator<CurvePoint> curveExpectedIterator = listForMock().iterator();

		while(curveResultsIterator.hasNext()){
			CurvePoint curveResult = curveResultsIterator.next();
			CurvePoint curveExpected =  curveExpectedIterator.next();
			Assertions.assertEquals(curveResult.getCurveId(),curveExpected.getCurveId());
			Assertions.assertEquals(curveResult.getId(),curveExpected.getId());
			Assertions.assertEquals(curveResult.getTerm(), curveExpected.getTerm());
			Assertions.assertEquals(curveResult.getValue(),(curveExpected.getValue()));
		}
	}

	private Optional<CurvePoint> findById(Integer id){
		for(CurvePoint curve :listForMock()) {
			if(curve.getCurveId()== id.intValue()) {
				return Optional.of(curve);
			}
		}
		return Optional.empty();
	}
	/**
	 * get curvePoint by id
	 * @param id
	 * @return curvePoint by his id
	 */
	@Test
	void testGetCurvePointById() {
		//Given
		when(curveRepository.findById(CURVE_ID)).thenReturn(findById(CURVE_ID));

		//When
		Optional<CurvePoint> curveResult = curvePointService.getCurvePointById(CURVE_ID);

		//Then
		Assertions.assertEquals(true, curveResult.isPresent());
		Assertions.assertEquals(CURVE_ID.intValue(), curveResult.get().getCurveId());
		Assertions.assertEquals(CURVE_ID.intValue(), curveResult.get().getId());
		Assertions.assertEquals(1, curveResult.get().getTerm());
		Assertions.assertEquals(1,curveResult.get().getValue());
	}
	/**
	 * This test checks when we call saveCurvePoint method of the CurvePointService
	 * the save method of the repository is called
	 * 
	 * We mock the repository in order to capture the arguments of the saveCurvePoint method
	 * And we check if the captured value is equal to argument value (here curve4 instance)
	 * 
	 * @param curvePoint
	 * @return save or update curvePoint in BDD
	 */
	@Test
	void testSaveCurvePoint() throws Exception {
		//Given
		CurvePoint curve4 = new CurvePoint();
		curve4.setId(4);
		curve4.setCurveId(4);
		curve4.setTerm(4);
		curve4.setValue(4);

		//When
		ArgumentCaptor<CurvePoint> capturedCurvePointWhenSaveMethodIsCalled = ArgumentCaptor.forClass(CurvePoint.class);
		when(curveRepository.save(capturedCurvePointWhenSaveMethodIsCalled.capture())).thenReturn(curve4);

		curvePointService.saveCurvePoint(curve4);

		//Then
		CurvePoint capturedCurve = capturedCurvePointWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedCurve.getId());
		Assertions.assertEquals(4, capturedCurve.getCurveId());
		Assertions.assertEquals(4, capturedCurve.getTerm());
		Assertions.assertEquals(4, capturedCurve.getValue());

	}	
	/**
	 * delete curvePoint
	 * @param curvePoint
	 */
	@Test
	void testDeleteCurvePoint() {
		//TODO appeler le delete et verifier que l'element n'existe plus
		CurvePoint curvePoint1 = new CurvePoint();
		curvePoint1.setId(1);
		curvePoint1.setCurveId(1);
		curvePoint1.setTerm(1);
		curvePoint1.setValue(1);
		
		//when
		ArgumentCaptor<CurvePoint> capturedCurvePointWhenDeleteMethodIsCalled =ArgumentCaptor.forClass(CurvePoint.class);
		Mockito.doNothing().when(curveRepository).delete(capturedCurvePointWhenDeleteMethodIsCalled.capture());

		curvePointService.deleteCurvePoint(curvePoint1);
		//then
		CurvePoint capturedCurvePoint = capturedCurvePointWhenDeleteMethodIsCalled.getValue();
		Assertions.assertEquals(1, capturedCurvePoint.getId());
		Assertions.assertEquals(1, capturedCurvePoint.getCurveId());
		Assertions.assertEquals(1, capturedCurvePoint.getTerm());
		Assertions.assertEquals(1,  capturedCurvePoint.getValue());
	}
}
