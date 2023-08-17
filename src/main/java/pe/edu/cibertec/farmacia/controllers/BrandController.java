package pe.edu.cibertec.farmacia.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.cibertec.farmacia.entities.Brand;
import pe.edu.cibertec.farmacia.repositories.BrandRepository;

@Controller
@RequestMapping("marcas")
public class BrandController {

    BrandRepository brandRepository;
    
    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public String list(Model model) {
        List<Brand> brands = brandRepository.findAll();
        model.addAttribute("brands", brands);
        return "brand/list";
    }

    @GetMapping("registrar")
    public String showFormRegister(Model model) {
        model.addAttribute("brand", new Brand());
        return "brand/register";
    }

    @PostMapping
    public String register(Brand brand) {
        brandRepository.save(brand);
        return "redirect:/marcas";
    }
}
