package com.sale.shop.view;

import com.sale.shop.entity.Product;
import com.sale.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    ProductService productService;

    @RequestMapping(path="/", method=RequestMethod.GET)
    public String viewHomePage(ModelMap model) {
        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @RequestMapping(path="/edit", method=RequestMethod.GET)
    public String showEditProductPage(@RequestParam(value="id", required=false) String id, ModelMap model) {
        Product product;
        if (id == null) {
            product = new Product();
        } else {
            product = productService.getById(Long.valueOf(id));
            model.addAttribute("id", id);
        }
        model.addAttribute("product", product);
        return "edit_product";
    }

    @RequestMapping(path="/save", method=RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        //After the product is inserted into the database, it redirects to the homepage to refresh the product list.
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") int id) {
        productService.deleteById(id);
        return "redirect:/";
    }
}
