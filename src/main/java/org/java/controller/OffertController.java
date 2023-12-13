package org.java.controller;

import org.java.db.pojo.Offert;
import org.java.db.pojo.Pizza;
import org.java.db.serv.OffertService;
import org.java.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class OffertController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OffertService offertService;

	@GetMapping("/offert/{id}/create")
	public String createOffert(Model model) {

		Offert offert = new Offert();
		model.addAttribute("offert", offert);

		return "offert-form";
	}

	@PostMapping("/offert/{id}/create")
	public String createOffert(Model model, @Valid @ModelAttribute Offert offert, @PathVariable int id,
			BindingResult bindingResult) {

		Pizza pizza = pizzaService.findById(id);

		return saveOffert(model, offert, pizza, bindingResult);

	}

	@GetMapping("/offert/edit/{id}")
	public String editOffert(Model model, @PathVariable int id) {

		Offert offert = offertService.findById(id);
		model.addAttribute("offert", offert);
		return "offert-form";
	}

	@PostMapping("/offert/edit/{id}")
	public String updateOffert(Model model, @Valid @ModelAttribute Offert offert, @PathVariable int id,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			System.out.println("resta qui");
			model.addAttribute("offert", offert);
			return "offert-form";
		}

		Pizza pizza = pizzaService.findById(id);

		// SALVARE LA MODIFICA PASSANDOGLI LA PIZZA

		offert.setPizza(pizza);

		offertService.save(offert);

		return "redirect:/pizza/" + id;
	}

	@PostMapping("/offert/delete/{id}")
	public String deleteOff(Model model, @PathVariable int id) {

		Offert off = offertService.findById(id);

		Pizza pizza = off.getPizza();

		offertService.delete(off);

		return "redirect:/pizza/" + pizza.getId();

	}

	public String saveOffert(Model model, @Valid @ModelAttribute Offert offert, Pizza pizza,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("offert", offert);
			return "offert-form";
		}

		Offert newOffert = new Offert(offert.getTitle(), offert.getStartDateOffert(), offert.getEndDateOffert(), pizza);
		offertService.save(newOffert);

		return "redirect:/pizza/" + pizza.getId();
	}

}
