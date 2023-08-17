package pe.edu.cibertec.farmacia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.farmacia.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
