package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.fail;
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
	CurvePointService curveService = new CurvePointService();


	public List<CurvePoint> listForMock() {

		CurvePoint curve1 = new CurvePoint();
		curve1.setCurveId(1);
		curve1.setId(1);
		curve1.setTerm(10);
		curve1.setValue(10);

		CurvePoint curve2 = new CurvePoint();
		curve2.setCurveId(2);
		curve2.setId(2);
		curve2.setTerm(12);
		curve2.setValue(12);

		CurvePoint curve3 = new CurvePoint();
		curve3.setCurveId(3);
		curve3.setId(3);
		curve3.setTerm(13);
		curve3.setValue(13);

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
		Iterable<CurvePoint> curveResults = curveService.getCurvePoints();

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
		Optional<CurvePoint> curveResult = curveService.getCurvePointById(CURVE_ID);

		//Then
		Assertions.assertEquals(true, curveResult.isPresent());
		Assertions.assertEquals(CURVE_ID.intValue(), curveResult.get().getCurveId());
		Assertions.assertEquals(CURVE_ID.intValue(), curveResult.get().getId());
		Assertions.assertEquals(10, curveResult.get().getTerm());
		Assertions.assertEquals(10,curveResult.get().getValue());
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
		curve4.setTerm(14);
		curve4.setValue(14);

		//When
		ArgumentCaptor<CurvePoint> capturedCurvePointWhenSaveMethodIsCalled = ArgumentCaptor.forClass(CurvePoint.class);
		Mockito.doNothing().when(curveRepository).save(capturedCurvePointWhenSaveMethodIsCalled.capture());

		curveService.saveCurvePoint(curve4);

		//Then
		CurvePoint capturedCurve = capturedCurvePointWhenSaveMethodIsCalled.getValue();
		Assertions.assertEquals(4, capturedCurve.getId());
		Assertions.assertEquals(4, capturedCurve.getCurveId());
		Assertions.assertEquals(14, capturedCurve.getTerm());
		Assertions.assertEquals(14, capturedCurve.getValue());

	}	

	//	/**
	//	 * Save curvePoint
	//	 * @param curvePoint
	//	 * @return save or update curvePoint in BDD
	//	 */
	//	@Test
	//	void testSaveCurvePoint() throws Exception {
	//		//TODO Créer un element et l'ajouter a mon repo par mon service + appeler méthode saveBidList puis appeler getBidLists et verifier que element est dedans
	//
	//		//Given
	//		CurvePoint curve4 = new CurvePoint();
	//		curve4.setCurveId(4);
	//		curve4.setId(4);
	//		curve4.setTerm(14);
	//		curve4.setValue(14);
	//
	//		//When
	//		when(curveRepository.findAll()).thenReturn(listForMock());
	//		curveService.saveCurvePoint(curve4);
	//		
	//		//Then
	//		Iterable<CurvePoint> curveResult = curveService.getCurvePoints();
	//		boolean curveFound = false;
	//		for(CurvePoint curve : curveResult) {
	//			if(curve.getCurveId()== 4) {
	//				curveFound = true;
	//			}
	//		}
	//		Assertions.assertEquals(true, curveFound);
	//	}	
	//
	//	/**
	//	 * Save all list of CurvePoint
	//	 * @param curvePoints
	//	 * @return save all curvePoints
	//	 */
	//	@Test
	//	void testSaveAllCurvePoint() {
	//		//TODO create an element and add it to the repo by service + call saveAllBidList and call getBidLists and verify element is in it
	//		fail("not yet implemented");
	//	}
	//
	//	private void deleteById(Integer id) {
	//		
	//		CurvePoint curveToDelete = null;
	//		for(CurvePoint curve :listForMock()) {
	//			if(curve.getCurveId() == id.intValue()) {
	//				curveToDelete = curve;
	//			}
	//		}
	//		listForMock().remove(curveToDelete);
	//	}
	//	
	//	/**
	//	 * delete curvePoint by id
	//	 * @param id
	//	 */
	//	@Test
	//	void testDeleteCurvePointById() {
	//		//TODO call deleteById and verify element don't exist
	//		//Given
	//		when(curveRepository.deleteById(CURVE_ID)).thenCall(deleteById(CURVE_ID));
	//
	//		//When
	//		curveService.deleteCurvePointById(CURVE_ID);
	//
	//		//Then
	//		Iterable<CurvePoint> result = curveService.getCurvePoints();
	//		boolean curveFound = false;
	//
	//		for(CurvePoint curve : result) {
	//			if(curve.getCurveId()==1) {
	//				curveFound = true;
	//			}
	//		}
	//		Assertions.assertFalse(curveFound);
	//	}
	//
	//	
	//	/**
	//	 * delete curvePoint
	//	 * @param curvePoint
	//	 */
	//	@Test
	//	void testDeleteCurvePoint() {
	//		//TODO appeler le delete et verifier que l'element n'existe plus
	//		fail("not yet implemented");
	//	}
}
