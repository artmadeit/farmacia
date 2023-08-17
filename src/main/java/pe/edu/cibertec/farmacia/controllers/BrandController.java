package pe.edu.cibertec.farmacia.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import pe.edu.cibertec.farmacia.entities.Brand;
import pe.edu.cibertec.farmacia.repositories.BrandRepository;
import pe.edu.cibertec.farmacia.services.ReportService;

@Controller
@RequestMapping("marcas")
public class BrandController {

    BrandRepository brandRepository;
    ReportService reportService;
    
    public BrandController(BrandRepository brandRepository, ReportService reportService) {
        this.brandRepository = brandRepository;
        this.reportService = reportService;
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

    @GetMapping("{id}")
    public String showFormEdit(@PathVariable Integer id, Model model) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if(brandOptional.isEmpty()) {
            return "not-found";            
        }

        model.addAttribute("brandForm", brandOptional.get());
        return "brand/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Integer id, Brand brandForm) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if(brandOptional.isEmpty()) {
            return "not-found";            
        }

        Brand brand = brandOptional.get();
        brand.setName(brandForm.getName());
        brand.setDescription(brandForm.getDescription());
        brandRepository.save(brand);

        return "redirect:/marcas";
    }

    @GetMapping("{id}/eliminar")
    public String delete(@PathVariable Integer id) {
        brandRepository.deleteById(id);
        return "redirect:/marcas";
    }

    @GetMapping("reporte")
    public void downloadReport(HttpServletResponse response) {
        reportService.downloadReport(response, "reporte_marcas.jasper");
    }


}
