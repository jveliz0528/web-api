package ar.com.educacionit.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.eduacionit.app.ws.meli.client.CategoryRestExecutor;
import ar.com.eduacionit.app.ws.meli.client.dto.cetegory.Categoria;

@Controller
public class MeliController {

	@RequestMapping("/meli/categorias")
	public String meliCategorias(Model model) { 
		CategoryRestExecutor cre = new CategoryRestExecutor("https://api.mercadolibre.com/categories/MLA1071");
		
		try {
			Categoria categoria = cre.executeRestCall();
			model.addAttribute("categorias", categoria);
			return  "meli_categorias";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
