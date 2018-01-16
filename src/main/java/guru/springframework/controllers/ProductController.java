package guru.springframework.controllers;

import guru.springframework.services.PageViewEventService;
import guru.springframework.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 1/20/16.
 */
@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    private final PageViewEventService pageViewEventService;

    @Autowired
    public ProductController(ProductService productService, PageViewEventService pageViewEventService) {
        this.productService = productService;
        this.pageViewEventService = pageViewEventService;
    }

    @RequestMapping("/product/{id}")
    public String getProductById(@PathVariable Integer id, Model model) {

        model.addAttribute("product", productService.getProduct(id));

        //Send Page view event
        pageViewEventService.sendPageViewEvent(id);

        return "product";
    }
}
