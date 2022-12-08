package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	CurvePointRepository curvePointRepository;
	
	/**
	 * Get curvePoints
	 * @return list of curvePoint
	 */
	public Iterable<CurvePoint> getCurvePoints() {
		return curvePointRepository.findAll();
	}
	
	/**
	 * Get curvePoint by id
	 * @param id
	 * @return CurvePoint by id
	 */
	public Optional<CurvePoint> getCurvePointById(Integer id) {
		return curvePointRepository.findById(id);
	}
	
	/**
	 * save curvePoint
	 * @param curvePoint
	 * @return curvePoint
	 */
	public  CurvePoint saveCurvePoint(CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}
	
	/**
	 * delete CurvePoint
	 * @param curvePoint
	 */
	public void deleteCurvePoint(CurvePoint curvePoint) {
		curvePointRepository.delete(curvePoint);
	}
}