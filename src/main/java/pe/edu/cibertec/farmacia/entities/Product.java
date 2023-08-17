package pe.edu.cibertec.farmacia.entities;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    String name;
    Integer stock;
    BigDecimal price;
    String descripcion;
    List<String> urlImages;
    Brand brand;
}
