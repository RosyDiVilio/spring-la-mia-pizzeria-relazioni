package org.lessons.java.pizzeria.controller;

import org.lessons.java.pizzeria.model.Ingredient;
import org.lessons.java.pizzeria.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService service;
	
	@GetMapping("")
    public String index(Model model) {
		
		model.addAttribute("ingredients", service.findAll());
		return "/ingredients/index";
	}
	
	@GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("ingredient", service.getById(id));
		return "/ingredients/show";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute(new Ingredient());
		return "/ingredients/create";
	}
	
	@PostMapping("/create")
		public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, 
	                        BindingResult bindingResult,
	                        Model model,
	                        RedirectAttributes attributes) {
		
		if(bindingResult.hasErrors()) {
			return "/ingredients/create";
		} 
		
		service.create(formIngredient);
		attributes.addFlashAttribute("successMessage", formIngredient.getName() + " has been added successfully");
		return "redirect:/ingredients";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("ingredient", service.getById(id));
		return "/ingredients/edit";
	}
	
	@PostMapping("/edit/{id}")
		public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, 
	                        BindingResult bindingResult,
	                        Model model,
	                        RedirectAttributes attributes) {
		
		if(bindingResult.hasErrors()) {
			return "/ingredients/edit";
		} 
		
		service.update(formIngredient);
		attributes.addFlashAttribute("successMessage", formIngredient.getName() + " has been updated successfully");
		return "redirect:/ingredients";
	}
	
	@PostMapping("/delete/{id}") 
	 public String delete(@PathVariable("id") Integer id,
			              RedirectAttributes attributes) {
	 
	 //prendi la repo e dopo aver trovato la pizza tramite l'id, cancellalo dal DB
	 service.deleteById(id);
		 
	 //ridireziona l'utente alla index delle pizze
	     attributes.addFlashAttribute("successMessage", "Ingredient with id " + id + " has been deleted successfully");
		 return "redirect:/ingredients";
	 }

}
