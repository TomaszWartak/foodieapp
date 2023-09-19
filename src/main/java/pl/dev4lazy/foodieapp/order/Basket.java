package pl.dev4lazy.foodieapp.order;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.dev4lazy.foodieapp.item.Item;

@Component
@SessionScope
public class Basket {

    private Order order;

    public Basket() {
        clear();
    }

    public void add(Item item) {
        order.getItems().add(item);
    }

    public void clear() {
        order = new Order();
        order.setStatus(OrderStatus.NEW);
    }

}
