package ar.com.educacionit.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.educacionit.app.domain.TipoProducto;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long>{

}
