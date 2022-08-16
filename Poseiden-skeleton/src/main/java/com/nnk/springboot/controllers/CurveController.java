package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class CurveController {
	
    @Autowired
    CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model){
        // find all Curve Point, add to model
    	Iterable<CurvePoint> curvePoints = curvePointService.getCurvePoints();
		model.addAttribute("curvePoints", curvePoints);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint, Model model) {
    	model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Curve list
    	if(result.hasErrors()) {
			return "curvePoint/add" ;    	
		}
		curvePointService.saveCurvePoint(curvePoint);
		model.addAttribute(curvePoint);
		model.addAttribute("curvePoints", curvePoint);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get CurvePoint by Id and to model then show to the form
    	CurvePoint curvePointFoundById = curvePointService.getCurvePointById(id).get() ;
		model.addAttribute(curvePointFoundById);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // check required fields, if valid call service to update Curve and return Curve list
    	if(result.hasErrors()) {
			return "curvePoint/update" ;    	
		}
		CurvePoint curvePointFoundById = curvePointService.getCurvePointById(id).get();
		curvePointFoundById.setCurveId(curvePoint.getCurveId());
		curvePointFoundById.setTerm(curvePoint.getTerm());
		curvePointFoundById.setValue(curvePoint.getValue());
		curvePointService.saveCurvePoint(curvePointFoundById);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        // Find Curve by Id and delete the Curve, return to Curve list
    	curvePointService.deleteCurvePointById(id);
		Iterable<CurvePoint> curvePoints = curvePointService.getCurvePoints();
		model.addAttribute(curvePoints);
        return "redirect:/curvePoint/list";
    }
}