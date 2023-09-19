package pl.dev4lazy.foodieapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dev4lazy.foodieapp.item.Item;
import pl.dev4lazy.foodieapp.item.ItemRepository;
import pl.dev4lazy.foodieapp.order.Basket;
import pl.dev4lazy.foodieapp.order.OrderRepository;
import pl.dev4lazy.foodieapp.utils.Message;

import java.util.Optional;

@Controller
public class OrderController{

    private Basket basket;
    private ItemRepository itemRepository;

    public OrderController(Basket basket, ItemRepository itemRepository) {
        this.basket = basket;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/zamowienie/dodaj")
    public String addItemToOrder(@RequestParam Long itemId, Model model) {
        Optional<Item> itemFound = itemRepository.findById( itemId );
        itemFound.ifPresent( basket::add );
        if (itemFound.isPresent()) {
            model.addAttribute(
                    "addItemResultMessage",
                    new Message( "Dodano", "Do zamówienia dodano: "+ itemFound.get().getName() )
            );
        } else {
            model.addAttribute(
                    "addItemResultMessage",
                    new Message( "Nie dodano", "Wybrano nieprawidłową pozycję z menu: "+ itemId )
            );
        }
        return "add-item-message";
    }
}
