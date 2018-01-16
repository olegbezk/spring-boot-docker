package guru.springframework.controllers;

import guru.springframework.services.PageViewEventService;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 1/20/16.
 */
@Controller
public class IndexController {

    private final ProductService productService;

    private final PageViewEventService pageViewEventService;

    @Autowired
    public IndexController(final ProductService productService, final PageViewEventService pageViewEventService) {
        this.productService = productService;
        this.pageViewEventService = pageViewEventService;
    }

    @RequestMapping({"/", "index"})
    public String getIndex(Model model){

        model.addAttribute("products", productService.listProducts());

        //Send Page view event
        pageViewEventService.sendPageViewEvent();

        return "index";
    }

}
