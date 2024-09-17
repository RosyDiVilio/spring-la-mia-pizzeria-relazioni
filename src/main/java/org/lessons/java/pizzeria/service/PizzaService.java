package org.lessons.java.pizzeria.service;

import java.util.List;

import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository repo;
	
	public List<Pizza> findAllSortedByNewer() {
		return repo.findAll(Sort.by("name"));
    }
	
	public Pizza getById (Integer id) {
		return repo.findById(id).get();
	}
	
	public Pizza create(Pizza pizza) {
		return repo.save(pizza);
	}
	
	public Pizza update(Pizza pizza) {
		return repo.save(pizza);
	}
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}