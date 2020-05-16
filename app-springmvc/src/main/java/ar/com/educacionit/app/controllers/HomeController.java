package ar.com.educacionit.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.educacionit.app.repository.ProductoRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ProductoRepository pr;
	
	@RequestMapping(value = "/")
	public String home() {
		
		System.out.println(pr.findAll());
		
		return "index";
	}
	
	public String list(Model model) {
		
		//cargar los productos
		model.addAttribute("productos", pr.findAll());
		
		return "litado";
	}
}
