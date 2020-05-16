package ar.com.eduacionit.rest.springboot;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.educacionit.app.domain.Producto;
import ar.com.educacionit.services.ProductoService;
import ar.com.educacionit.services.exceptions.ServiceException;
import ar.com.educacionit.services.impl.ProductoServiceImpl;

@RestController
@RequestMapping(path = "/productos")
public class ProductoResource {

	private ProductoService productoService = new ProductoServiceImpl();
	
	@GetMapping
    public ResponseEntity<List<Producto>> findProcuctos() 
    {
		
		try {
			List<Producto> productos = productoService.findProductos();
			return ResponseEntity.ok(productos);
		} catch (ServiceException e) {
			return null;
		}
    }
}
