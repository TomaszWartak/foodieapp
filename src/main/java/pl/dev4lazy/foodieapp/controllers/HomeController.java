package pl.dev4lazy.foodieapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.dev4lazy.foodieapp.item.ItemRepository;

import java.util.List;

@Controller
public class HomeController {

    private ItemRepository itemRepository;

    public HomeController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String showHomeView(Model model) {
        List items = itemRepository.findAll();
        model.addAttribute( "items", items);
        return "index";
    }

}
