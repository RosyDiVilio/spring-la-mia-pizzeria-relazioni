package org.lessons.java.pizzeria.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offers")
public class Offer {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String title;
	
	@NotNull
	private LocalDate startOffer;
	
	@NotNull
	private LocalDate endOffer;
	
	//il tipo di relazione che stabiliamo con quest'altro modello
	@ManyToOne
	//la colonna che fungerà da chiave esterna per il modello relazionato
	@JoinColumn(name = "pizza_id", nullable= false)
	private Pizza pizza;
	
    //getter e setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartOffer() {
		return startOffer;
	}

	public void setStartOffer(LocalDate startOffer) {
		this.startOffer = startOffer;
	}

	public LocalDate getEndOffer() {
		return endOffer;
	}

	public void setEndOffer(LocalDate endOffer) {
		this.endOffer = endOffer;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}
