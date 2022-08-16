package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

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
public class RuleNameController {

	@Autowired
	RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // find all RuleName, add to model
    	Iterable<RuleName> ruleNames = ruleNameService.getRuleNames();
    	model.addAttribute("ruleNames", ruleNames);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName, Model model) {
    	model.addAttribute(ruleName);
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // check data valid and save to db, after saving return RuleName list
    	if(result.hasErrors()) {
        return "ruleName/add";
    	}
    	ruleNameService.saveRuleName(ruleName);
    	model.addAttribute(ruleName);
    	model.addAttribute("ruleNames", ruleName);
    	return "ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get RuleName by Id and to model then show to the form
    	RuleName ruleNameFoundById = ruleNameService.getRuleNameById(id).get();
    	model.addAttribute(ruleNameFoundById);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // check required fields, if valid call service to update RuleName and return RuleName list
    	if(result.hasErrors()) {
    		return "ruleName/update";
    	}
    	RuleName ruleNameFoundById = ruleNameService.getRuleNameById(id).get();
    	ruleNameFoundById.setName(ruleName.getName());
    	ruleNameFoundById.setDescription(ruleName.getDescription());
    	ruleNameFoundById.setJson(ruleName.getJson());
    	ruleNameFoundById.setTemplate(ruleName.getTemplate());
    	ruleNameFoundById.setSqlStr(ruleName.getSqlStr());
    	ruleNameFoundById.setSqlPart(ruleName.getSqlPart());
    	ruleNameService.saveRuleName(ruleNameFoundById);
    	model.addAttribute("ruleName", ruleNameFoundById);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // Find RuleName by Id and delete the RuleName, return to Rule list
    	ruleNameService.deleteRuleNameById(id);
    	Iterable<RuleName> ruleNames = ruleNameService.getRuleNames();
    	model.addAttribute(ruleNames);
        return "redirect:/ruleName/list";
    }
}