package ar.com.educacionit.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.com.educacionit.app.domain.Producto;
import ar.com.educacionit.app.repository.ProductoRepository;
import ar.com.educacionit.app.repository.TipoProductoRepository;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TipoProductoRepository tipoProductoRepository;
	
	@RequestMapping("/list")
	public String listado(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "listado";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(
			@PathVariable(name = "id")
			Long id
			) {
		
		Producto p = new Producto();
		p.setId(id);
		
		productoRepository.delete(p);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/new")
	public ModelAndView newProduct () {
		
		Producto p = new Producto();
		
		ModelAndView modelAndView = new ModelAndView("new_product");
		modelAndView.addObject("producto", p);
		modelAndView.addObject("tiposProductos", tipoProductoRepository.findAll());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute(name = "producto") 
			Producto producto) {

		productoRepository.save(producto);
		return "redirect:/list";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(
			@PathVariable(name = "id")
			Long id,
			Model model
			) {
		
		model.addAttribute("producto", productoRepository.getOne(id));
		model.addAttribute("tiposProductos", tipoProductoRepository.findAll());
		
		return "edit";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(
			@ModelAttribute(name = "producto") 
			Producto producto
			) {
		
		productoRepository.save(producto);
		return "redirect:/list";
	}
}
