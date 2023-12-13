package org.java;

import java.math.BigDecimal;

import org.java.auth.config.AuthConf;
import org.java.auth.db.Role;
import org.java.auth.db.User;
import org.java.auth.serv.RoleService;
import org.java.auth.serv.UserService;
import org.java.db.pojo.Ingredient;
import org.java.db.pojo.Pizza;
import org.java.db.serv.IngredientService;
import org.java.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Ingredient ingr1 = new Ingredient("Carciofi");
		Ingredient ingr2 = new Ingredient("Mozzarella");
		Ingredient ingr3 = new Ingredient("Pomodoro");
		Ingredient ingr4 = new Ingredient("Funghi");
		Ingredient ingr5 = new Ingredient("Prosciutto");
		Ingredient ingr6 = new Ingredient("Olive");
		Ingredient ingr7 = new Ingredient("Peperoni");
		Ingredient ingr8 = new Ingredient("Salame");
		Ingredient ingr9 = new Ingredient("Cipolla");
		Ingredient ingr10 = new Ingredient("Salsiccia");
		Ingredient ingr11 = new Ingredient("Basilico");
		Ingredient ingr12 = new Ingredient("Melanzane");
		Ingredient ingr13 = new Ingredient("Parmigiano");
		Ingredient ingr14 = new Ingredient("Rucola");
		Ingredient ingr15 = new Ingredient("Tonno");
		Ingredient ingr16 = new Ingredient("Peperoncino");
		Ingredient ingr17 = new Ingredient("Ananas");
		Ingredient ingr18 = new Ingredient("Gorgonzola");
		Ingredient ingr19 = new Ingredient("Pomodori secchi");
		Ingredient ingr20 = new Ingredient("Zucchine");

		ingredientService.save(ingr1);
		ingredientService.save(ingr2);
		ingredientService.save(ingr3);
		ingredientService.save(ingr4);
		ingredientService.save(ingr5);
		ingredientService.save(ingr6);
		ingredientService.save(ingr7);
		ingredientService.save(ingr8);
		ingredientService.save(ingr9);
		ingredientService.save(ingr10);
		ingredientService.save(ingr11);
		ingredientService.save(ingr12);
		ingredientService.save(ingr13);
		ingredientService.save(ingr14);
		ingredientService.save(ingr15);
		ingredientService.save(ingr16);
		ingredientService.save(ingr17);
		ingredientService.save(ingr18);
		ingredientService.save(ingr19);
		ingredientService.save(ingr20);

		Pizza pizza1 = new Pizza("Capricciosa", "La pizza tutta capricciosa",
				"https://picsum.photos/seed/picsum/700/500", new BigDecimal("11.50"), ingr1, ingr3, ingr4, ingr10);

		Pizza pizza2 = new Pizza("Margherita", "Bona come la regina", "https://picsum.photos/seed/picsum/700/500",
				new BigDecimal("9.99"));

		Pizza pizza3 = new Pizza("Quattro Stagioni", "Ormai non ce son più",
				"https://picsum.photos/seed/picsum/700/500", new BigDecimal("12.75"), ingr7, ingr8, ingr12, ingr15);

		Pizza pizza4 = new Pizza("Diavola", "Quella che vorremmo tutti", "https://picsum.photos/seed/picsum/700/500",
				new BigDecimal("10.25"), ingr19, ingr20, ingr4, ingr10);

		Pizza pizza5 = new Pizza("Quattro Formaggi", "La puzzona", "https://picsum.photos/seed/picsum/700/500",
				new BigDecimal("13.20"), ingr9, ingr8, ingr2, ingr3);

		Pizza pizza6 = new Pizza("Napoletana", "Aho! gnamme belli", "https://picsum.photos/seed/picsum/700/500",
				new BigDecimal("11.75"), ingr8, ingr3, ingr14, ingr17);

		Pizza pizza7 = new Pizza("Calzone", "Quello di Babbo Natale", "https://picsum.photos/seed/picsum/700/500",
				new BigDecimal("10.99"), ingr17, ingr15, ingr14, ingr10);

		Pizza pizza8 = new Pizza("Hawaiian", "Dove vorremmo essere tutti", "https://picsum.photos/seed/picsum/700/500",
				new BigDecimal("10.50"), ingr16, ingr15, ingr14, ingr20);

		Pizza pizza9 = new Pizza("Vegetariana", "Magnatela te!", "https://picsum.photos/seed/picsum/700/500",
				new BigDecimal("11.00"), ingr16, ingr13, ingr14, ingr10);

		Pizza pizza10 = new Pizza("Bianca", "is White", "https://picsum.photos/seed/picsum/700/500",
				new BigDecimal("9.75"), ingr2, ingr5, ingr9, ingr13);

		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);
		pizzaService.save(pizza6);
		pizzaService.save(pizza7);
		pizzaService.save(pizza8);
		pizzaService.save(pizza9);
		pizzaService.save(pizza10);

		Role role1 = new Role("USER");
		Role role2 = new Role("ADMIN");

		roleService.save(role1);
		roleService.save(role2);

		String psw = AuthConf.passwordEncoder().encode("psw");

		User user1 = new User("user", psw, role1);
		User user2 = new User("admin", psw, role2);

		userService.save(user1);
		userService.save(user2);

	}

}
