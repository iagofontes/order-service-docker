package br.com.fiap.orderservice.repositories;

import br.com.fiap.orderservice.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    private List<Order> orders;

    public OrderRepository(List<Order> orders) {
        this.orders = orders;
    }

    public boolean updateOrder(Order order) {
        int index = this.orders.indexOf(order);

        if (index == -1) {
            return false;
        }

        this.orders.set(index, order);
        return true;
    }


    public boolean deleteOrder(int id) {
        Order order = new Order(id);
        int index = this.orders.indexOf(order);

        if (index == -1) {
            return false;
        }

        this.orders.remove(index);
        return true;
    }


    public Order addOrder(Order order) {
        this.orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return this.orders;
    }
}
