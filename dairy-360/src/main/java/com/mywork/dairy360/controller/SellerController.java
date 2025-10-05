package com.mywork.dairy360.controller;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.dto.ProductDTO;
import com.mywork.dairy360.dto.SellerDTO;
import com.mywork.dairy360.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private MailService mailService;

    @Autowired
    private SellerAuditService sellerAuditService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private ProductServiceImpl productService;


    public SellerController(){
        System.out.println("constructor SellerRestController");
    }

    private void addAdminDtoToModel(HttpSession session, Model model) {

        String email = (String) session.getAttribute("adminEmail");
        if (email != null) {
            AdminDTO dto = adminService.getAdminDetailsByEmail(email);
            model.addAttribute("dto", dto);
        }
    }

    @GetMapping("list")
    public String listSellers(
            @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "5") int size,
                               HttpSession session, Model model) {
        System.out.println("listSeller method in SellerRestController - page=" + page + " size=" + size);
        model.addAttribute("sellers", sellerService.getAllActiveSellers());

        addAdminDtoToModel(session, model);
        String email = (String) session.getAttribute("adminEmail");
        if (email != null) {
            AdminDTO dto = adminService.getAdminDetailsByEmail(email);
            model.addAttribute("dto", dto);
        }

        List<SellerDTO> sellers = sellerService.getAllActiveSellers(page, size);
        long total = sellerService.countActiveSellers();
        int totalPages = (int) Math.ceil((double) total / size);

        model.addAttribute("sellers", sellers);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("total", total);

        addAdminDtoToModel(session, model);
        return "seller";
    }


    @GetMapping("add")
    public String showAddForm(HttpSession session, Model model) {
        System.out.println("showAddForm method in SellerRestController");
        model.addAttribute("seller", new SellerDTO());

        List<ProductDTO> sellProducts = productService.getSellProducts();
        model.addAttribute("sellProducts", sellProducts);

        addAdminDtoToModel(session, model);
        return "sellerForm";
    }

    @PostMapping("save")
    public String saveSeller(@ModelAttribute SellerDTO sellerDto,
                             HttpSession session,
                             RedirectAttributes redirectAttrs) {

        System.out.println("saveSeller method in controller");
        String adminEmail = (String) session.getAttribute("adminEmail");
        AdminDTO adminDto = null;
        if (adminEmail != null) {
            adminDto = adminService.getAdminDetailsByEmail(adminEmail);
        }

        boolean isNew = sellerDto.getSellerId() == null;
        boolean saved = sellerService.saveSeller(sellerDto,
                adminDto != null ? adminDto : null);

        if (saved) {
            if (isNew) {
                mailService.sendMail(sellerDto.getEmail(),
                        "Welcome to Dairy360",
                        "Thank you " + sellerDto.getFirstName() + " for registering as a Seller!");
            }

            redirectAttrs.addFlashAttribute("successMessage",
                    isNew ? "Seller added successfully!" : "Seller updated successfully!");
        } else {
            redirectAttrs.addFlashAttribute("errorMessage",
                    isNew ? "Failed to add seller!" : "Failed to update seller!");
        }
        return "redirect:/list";
    }

    @GetMapping("edit/{id}")
    public String editSeller(@PathVariable Integer id, HttpSession session, Model model) {
        System.out.println("editSeller method in SellerRestController");
        SellerDTO seller = sellerService.getSellerById(id);
        if (seller == null) {
            model.addAttribute("errorMessage", "Seller not found");
            return "redirect:/list";
        }
        model.addAttribute("seller", seller);

        List<ProductDTO> sellProducts = productService.getSellProducts();
        model.addAttribute("sellProducts", sellProducts);

        addAdminDtoToModel(session, model);
        return "editSeller";
    }


    @GetMapping("delete/{id}")
    public String deleteSeller(@PathVariable Integer id,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               HttpSession session,
                               RedirectAttributes redirectAttrs) {
        System.out.println("deleteSeller method in SellerRestController");
        String adminName = (String) session.getAttribute("adminName");
        try {
            boolean deleted = sellerService.softDeleteSeller(id, adminName);
            if (deleted) {
                redirectAttrs.addFlashAttribute("successMessage", "Seller deleted!");
            } else {
                redirectAttrs.addFlashAttribute("errorMessage", "Seller not found!");
            }
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorMessage", "Error deleting seller: " + e.getMessage());
        }
        return "redirect:/list?page=" + page + "&size=" + size;
    }


    @GetMapping("audit")
    public String sellerAudit(HttpSession session, Model model) {
        System.out.println("sellerAudit method in SellerRestController");
        addAdminDtoToModel(session, model);
        model.addAttribute("auditList", sellerAuditService.getAllAudits());
        System.out.println("allAudits: "+sellerAuditService.getAllAudits());
        return "sellerAudit";
    }
}