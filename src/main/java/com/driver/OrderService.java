package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void saveOrder(Order order){
        orderRepository.addOrder(order);
    }

    public void savePartner(String partnerId){
        orderRepository.addPartner(partnerId);
    }

    public void savePair(String orderId, String partnerId){
        orderRepository.addPair(orderId, partnerId);
    }

    public Order getOrder(String orderId){
        return orderRepository.getOrder(orderId);
    }

    public DeliveryPartner getPartner(String partnerId){
        return orderRepository.getPartner(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> orders = orderRepository.getOrdersByPartnerId(partnerId);
        return orders;
    }

    public List<String> getAllOrders(){
        List<String> orders = orderRepository.getAllOrders();
        return orders;
    }

    public int orderWithoutPartner(){
        return orderRepository.orderWithoutPartner();
    }

    public int getOrdersLeftAfterTime(String time, String partnerId){
        return orderRepository.getOrdersLeftAfterGivenTime(time,partnerId);
    }

    public String lastDeliveryTime(String partnerId){
        return orderRepository.lastDeliveryTime(partnerId);
    }

    public void deletePartner(String partnerId){
        orderRepository.deletePartner(partnerId);
    }

    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}
