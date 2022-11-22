package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(1, 10, null, 10d, 30d, null);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		Assertions.assertNotNull(curvePoint.getId());
		Assertions.assertTrue(curvePoint.getCurveId() == 10);

		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		Assertions.assertTrue(curvePoint.getCurveId() == 20);

		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		Assertions.assertFalse(curvePointList.isPresent());
	}
}