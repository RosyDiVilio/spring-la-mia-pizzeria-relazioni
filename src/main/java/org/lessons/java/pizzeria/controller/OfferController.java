package org.lessons.java.pizzeria.controller;

import org.lessons.java.pizzeria.model.Offer;
import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {
	
	@Autowired
	private OfferService service;
	
	@GetMapping()
	public String index (Model model) {
		
		model.addAttribute("offers", service.findAllSortedByTitle());
		return "/offers/index";
	}
	
	@PostMapping("/create")
		public String store(@Valid @ModelAttribute("offer") Offer formOffer, 
	                        BindingResult bindingResult,
	                        Model model,
	                        RedirectAttributes attributes) {
		
		if(bindingResult.hasErrors()) {
			return "/offers/create";
		} 
		
		service.create(formOffer);
		
		return "redirect:/pizzas/" + formOffer.getPizza().getId();
	}
		
	

}
