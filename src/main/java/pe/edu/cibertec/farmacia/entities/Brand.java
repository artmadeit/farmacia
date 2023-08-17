package pe.edu.cibertec.farmacia.entities;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String descripcion;
    @ElementCollection
    List<String> urlImages;

    public String getName() {
        return name;
    }

    public List<String> getUrlImages() {
        return urlImages;
    }
}