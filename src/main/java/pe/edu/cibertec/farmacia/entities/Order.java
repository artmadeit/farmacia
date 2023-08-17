package pe.edu.cibertec.farmacia.entities;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    String customerName;
    List<OrderLine> lines;

    /**
     * Esto define, una linea, por ejemplo: 2 sal de andrews, 0.6 soles = 1.2
     */
    class OrderLine {
        Integer quantity;
        Product product;
        BigDecimal unitPrice;
    }
}
