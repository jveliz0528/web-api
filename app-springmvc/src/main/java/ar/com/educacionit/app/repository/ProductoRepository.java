package ar.com.educacionit.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.educacionit.app.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	public List<Producto> findAllByDescripcion(String descripcion);
}
