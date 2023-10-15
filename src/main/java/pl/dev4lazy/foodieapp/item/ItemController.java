package pl.dev4lazy.foodieapp.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.dev4lazy.foodieapp.item.Item;
import pl.dev4lazy.foodieapp.item.ItemRepository;

import java.util.Optional;

@Controller
public class ItemController {

    private ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/dishes/{dishname}")
    public String showDishView(@PathVariable String dishname, Model model) {
        dishname = dishname.replaceAll("-", " ");
        Optional<Item> itemFound = itemRepository.findByNameIgnoreCase( dishname );
        itemFound.ifPresentOrElse(
                item -> model.addAttribute("item", item),
                () -> model.addAttribute( "responseCode", "404")
        );
        return itemFound.map(item -> "item").orElse("redirect:/error.html");
    }
}
