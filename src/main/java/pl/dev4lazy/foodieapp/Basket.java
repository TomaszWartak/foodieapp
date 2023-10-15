package pl.dev4lazy.foodieapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.dev4lazy.foodieapp.item.Item;
import pl.dev4lazy.foodieapp.order.Order;
import pl.dev4lazy.foodieapp.order.OrderStatus;

@Component
@SessionScope/*(proxyMode = ScopedProxyMode.TARGET_CLASS)*/
public class Basket {

    private Order order;

   /* @Autowired*/
    public Basket() {
       /* this.order = order;*/
        clear();
    }

    public void add(Item item) {
        order.getItems().add(item);
    }

    public void clear() {
        order = new Order();
        order.setStatus(OrderStatus.NEW);
    }

    public Order getOrder() {
        return order;
    }

/*    @Override
    public String toString() {
        return "Basket{" +
                "order=" + order +
                '}';
    }*/
}
