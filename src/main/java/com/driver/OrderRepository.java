package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String,Order> orderMapping = new HashMap<>();
    HashMap<String,DeliveryPartner> partnerHashMap = new HashMap<>();

    HashMap<String,Order> partnerOrderPair = new HashMap<>();

    HashMap<String,List<String>> partnerOrderListPair = new HashMap<>();

    HashMap<String,String> orderPartnerMap = new HashMap<>();
    List<String> orderList = new ArrayList<>();
    public void addOrder(Order order){
        orderMapping.put(order.getId(), order);
        orderList.add(order.getId());
    }

    public void addPartner(String partnerId){
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        List<String> orders = new ArrayList<>();
        partnerHashMap.put(partnerId,partner);
        partnerOrderListPair.put(partnerId,orders);
    }

    public void addPair(String orderId, String partnerId){
        partnerOrderPair.put(partnerId,orderMapping.get(orderId));
        List<String> orders = partnerOrderListPair.get(partnerId);
        orders.add(orderId);
        partnerOrderListPair.put(partnerId,orders);
        orderPartnerMap.put(orderId,partnerId);
    }

    public Order getOrder(String orderId){
        return orderMapping.get(orderId);
    }

    public DeliveryPartner getPartner(String partnerId){
        return partnerHashMap.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){
        DeliveryPartner partner = partnerHashMap.get(partnerId);
        return partner.getNumberOfOrders();
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> orderList = partnerOrderListPair.get(partnerId);
        return orderList;
    }

    public List<String> getAllOrders(){
        return orderList;
    }

    public int orderWithoutPartner(){
        return orderList.size() - partnerOrderPair.size();
    }

    public int getOrdersLeftAfterGivenTime(String time, String partnerId){
        List<String> orders = partnerOrderListPair.get(partnerId);
        int count = 0;
        int Time = Integer.parseInt(time);
        for(int i=0; i<orders.size(); i++){
            Order order = orderMapping.get(orders.get(i));
            if(order.getDeliveryTime() > Time){
                count++;
            }
        }
        return count;
    }

    public String lastDeliveryTime(String partnerId){
        int time = 0;
        List<String> orders = partnerOrderListPair.get(partnerId);
        for(int i=0; i<orders.size(); i++){
            Order order = orderMapping.get(orders.get(i));
            if(order.getDeliveryTime() > time){
                time = order.getDeliveryTime();
            }
        }
        return String.valueOf(time);
    }

    public void deletePartner(String partnerId){
        partnerOrderPair.remove(partnerId);
        partnerOrderListPair.remove(partnerId);

    }

    public void deleteOrderById(String orderId){
        orderMapping.remove(orderId);
        String partnerId = orderPartnerMap.get(orderId);
        partnerOrderPair.remove(partnerId,orderId);
        List<String> orders = partnerOrderListPair.get(partnerId);
        orders.remove(orderId);
        partnerOrderListPair.put(partnerId,orders);
    }
}
