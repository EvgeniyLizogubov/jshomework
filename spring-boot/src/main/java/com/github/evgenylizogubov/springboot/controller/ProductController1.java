package com.github.evgenylizogubov.springboot.controller;

import com.github.evgenylizogubov.springboot.model.Product;
import com.github.evgenylizogubov.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController1 {
    private ProductService productService;
    
    @Autowired
    public ProductController1(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public String getAll(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "products";
    }
    
    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product";
    }
    
    @GetMapping("/remove/{id}")
    public String removeById(@PathVariable int id) {
        productService.removeById(id);
        return "redirect:/products";
    }
    
    @GetMapping("/add")
    public String showCreatePage() {
        return "product_addition";
    }
    
    @GetMapping("/create")
    public String create(@RequestParam String name, @RequestParam int price) {
        Product product = new Product(name, price);
        productService.create(product);
        return "redirect:/products";
    }
}
