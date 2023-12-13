package org.java.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	@NotBlank(message = "The field is empty")
	private String name;

	@Column(columnDefinition = "TEXT")
	@Length(min = 3, message = "The description must be longer than 3 characters")
	private String description;

	@Column(columnDefinition = "TEXT")
	@URL
	private String photo;

	@Positive(message = "Invalid price")
	private float price;

	// RELAZIONE CON OFFERTA

	@OneToMany(mappedBy = "pizza")
	private List<Offert> offert;

	public List<Offert> getOffert() {
		return offert;
	}

	public void setOffert(List<Offert> offert) {
		this.offert = offert;
	}

	// RELAZIONE MANY TO MANY CON GLI INGREDIENT

	@ManyToMany
	private List<Ingredient> ingredients;

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	// METODO CHE RICEVE UNA LISTA DI INFREDIENTI TRAMITE LO SPRED OPERATOR E LO
	// STRASFORMA IN UN ARRAY
	@JsonIgnore
	public void setIngredients(Ingredient... ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}

	public Pizza() {
	}

	// AGGIUNGO AL COSTRUTTORE LA POSSIBILITA' DI RICEVERE UNA LISTA DI INFREDIENTI
	// CON LO SPRED OPERATOR
	public Pizza(String name, String description, String photo, float price, Ingredient... ingredients) {

		setName(name);
		setDescription(description);
		setPhoto(photo);
		setPrice(price);
		setIngredients(ingredients);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return getName() + " - " + getDescription() + " - " + getPrice() + " €";
	}

}
