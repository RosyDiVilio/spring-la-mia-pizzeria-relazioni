package org.lessons.java.pizzeria.service;

import java.util.List;

import org.lessons.java.pizzeria.model.Offer;
import org.lessons.java.pizzeria.repo.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
	
	@Autowired
	private OfferRepository repo;
	
	public List<Offer> findAllSortedByTitle() {
		return repo.findAll(Sort.by("title"));
	}
	
	public Offer create(Offer offer) {
		return repo.save(offer);
	}

}
