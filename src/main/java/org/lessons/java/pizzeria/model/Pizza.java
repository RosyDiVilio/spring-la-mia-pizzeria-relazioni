package org.lessons.java.pizzeria.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Formula;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//entity = crea una relazione con il DB
@Entity
@Table(name = "pizzas")
public class Pizza {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	  
    @NotNull
    @Size(min=5, max=250)
	@Column(name ="name", nullable = false)
	private String name;
    
    @NotNull
    @Size(min=5, max=250)
    @Column(name ="description", nullable = false)
	private String description;
    
    @NotNull
    @Size(min=5, max=250)
    private String foto;
	
    @NotNull
    @DecimalMax("30.00")
    @DecimalMin("5.00")
    @Column(name ="price", nullable = false)
    private float price;

    private LocalDateTime updatedAt;
    
    @NotNull
    @Max(250)
    private Integer numberOfOffers;

	//tipo di relazione da qualificare
    @OneToMany( mappedBy = "pizza", cascade = { CascadeType.REMOVE })
    private List<Offer> offers;
    
    //chiedo ad hibernate di eseguire una specifica query
    @Formula( "(select pizzas.number_of_offers - count(offers.id) " +
              "from pizzas " + 
    		  "left join offers " +
              "on pizzas.id = offers.pizza_id " +
    		  "where pizzas.id = id)")
    private Integer offersCanBeApplied;
    
  
    public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Integer getNumberOfOffers() {
		return numberOfOffers;
	}

	public void setNumberOfOffers(Integer numberOfOffers) {
		this.numberOfOffers = numberOfOffers;
	}

	public Integer getOffersCanBeApplied() {
		return offersCanBeApplied;
	}

	public void setOffersCanBeApplied(Integer offersCanBeApplied) {
		this.offersCanBeApplied = offersCanBeApplied;
	}

}
