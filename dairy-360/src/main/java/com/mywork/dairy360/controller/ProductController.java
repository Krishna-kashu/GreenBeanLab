package com.mywork.dairy360.controller;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.dto.ProductDTO;
import com.mywork.dairy360.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    public ProductController() {
        System.out.println("constructor ProductController");
    }

    @GetMapping("product")
    public String listProducts(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "5") int size,
                               Model model) {
        System.out.println("listProducts method in ProductController ");
        List<ProductDTO> products = productService.getAllActiveProducts(page, size);
        long total = productService.countActiveProducts();
        int totalPages = (int) Math.ceil((double) total / size);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("total", total);

        return "product";
    }


    @GetMapping("addProduct")
    public String redirectToAddProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "productForm";
    }

    @PostMapping("saveProduct")
    public String saveProduct(@ModelAttribute ProductDTO dto,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        AdminDTO adminDTO = (AdminDTO) session.getAttribute("adminDTO");

        if (adminDTO == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Admin not logged in!");
            return "redirect:/admin-login";
        }

        if (dto.getPrice() == null || dto.getPrice() < 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "Price must be a positive number");
            return "redirect:/addProduct";
        }

        boolean saved = productService.saveProduct(dto, adminDTO);
        if (saved) {
            redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to save product. Try again.");
        }
        return "redirect:/product";
    }

    @GetMapping("editProduct/{id}")
    public String editProduct(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        ProductDTO dto = productService.getProductById(id);
        if (dto == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found.");
            return "redirect:/product";
        }
        model.addAttribute("product", dto);
        return "editProduct";
    }

    @PostMapping("updateProduct")
    public String updateProduct(@ModelAttribute ProductDTO dto,
                                HttpSession session,
                                Model model) {

        AdminDTO adminDTO = (AdminDTO) session.getAttribute("adminDTO");
        if (adminDTO == null) {
            model.addAttribute("errorMessage", "Admin not logged in!");
            return "redirect:/admin-login";
        }

        boolean updated = productService.updateProduct(dto, adminDTO);
        if (updated) {
            model.addAttribute("successMessage", "Product updated successfully!");
        } else {
            model.addAttribute("errorMessage", "Failed to update product.");
        }
        return "redirect:/product";
    }

    @GetMapping("deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id,
                                HttpSession session,
                                Model model) {
        AdminDTO adminDTO = (AdminDTO) session.getAttribute("adminDTO");
        if (adminDTO == null) {
            model.addAttribute("errorMessage", "Admin not logged in!");
            return "redirect:/admin-login";
        }
        boolean deleted = productService.softDeleteProduct(id, adminDTO);
        if (deleted) {
            model.addAttribute("successMessage", "Product deleted successfully!");
        } else {
            model.addAttribute("errorMessage", "Failed to delete product.");
        }
        return "redirect:/product";
    }
}
