package org.java.db.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Offert {

	// PROPRIETA' E VALIDAZIONI

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@FutureOrPresent(message = "Invalid date")
	private LocalDate startDateOffert;

	@Future(message = "Invalid date")
	private LocalDate endDateOffert;

	@NotBlank(message = "Mandatory field")
	private String title;

	// RELAZIONE CON PIZZA

	@ManyToOne
	@JsonIgnore
	private Pizza pizza;

	// COSTRUTTORE

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Offert() {
	}

	public Offert(String title, LocalDate startDateOffert, LocalDate endDateOffert, Pizza pizza) {

		setTitle(title);
		setStartDateOffert(startDateOffert);
		setEndDateOffert(endDateOffert);

		setPizza(pizza);
	}

	// GETTER AND SETTER

	public LocalDate getStartDateOffert() {
		return startDateOffert;
	}

	public void setStartDateOffert(LocalDate startDateOffert) {
		this.startDateOffert = startDateOffert;
	}

	public LocalDate getEndDateOffert() {
		return endDateOffert;
	}

	public void setEndDateOffert(LocalDate endDateOffert) {
		this.endDateOffert = endDateOffert;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Offert [getStartDateOffert()=" + getStartDateOffert() + ", getEndDateOffert()=" + getEndDateOffert()
				+ ", getTitle()=" + getTitle() + "]";
	}

}
