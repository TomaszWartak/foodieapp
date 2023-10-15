package pl.dev4lazy.foodieapp.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dev4lazy.foodieapp.item.Item;
import pl.dev4lazy.foodieapp.item.ItemRepository;
import pl.dev4lazy.foodieapp.Basket;
import pl.dev4lazy.foodieapp.utils.Message;

import java.util.Optional;

@Controller
public class OrderController{

    private Basket basket;
    private ItemRepository itemRepository;

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(Basket basket, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.basket = basket;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/basket/add")
    public String addItemToBasket(@RequestParam Long itemId, Model model) {
        Optional<Item> itemFoundWrapped = itemRepository.findById( itemId );
        if (itemFoundWrapped.isPresent()) {
            Item item = itemFoundWrapped.get();
            basket.add( item );
            model.addAttribute(
                    "message",
                    new Message( "Dodano", "Do zamówienia dodano: "+ item.getName() )
            );
        } else {
            model.addAttribute(
                    "message",
                    new Message( "Nie dodano", "Wybrano nieprawidłową pozycję z menu: "+ itemId )
            );
        }
        return "message";
    }

    @GetMapping("/basket")
    public String showBasketView(Model model) {
        model.addAttribute("order", basket.getOrder());
        model.addAttribute("sum", basket
                .getOrder()
                .getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum());
        return "basket";
    }

    @PostMapping("/order/finalize")
    public String proceedOrder(@RequestParam String address, @RequestParam String telephone, Model model) {
        Order order = basket.getOrder();
        order.setAddress(address);
        order.setTelephone(telephone);
        orderRepository.save(order);
        basket.clear();
        model.addAttribute("message", new Message("Dziękujemy", "Zamówienie przekazane do realizacji"));
        return "message";
    }

    @GetMapping("/panel/orders")
    public String showClientOrdesView(@RequestParam OrderStatus orderStatus ) {

    }
}

