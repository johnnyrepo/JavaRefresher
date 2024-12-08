package concurrency.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

public class WarehouseExecutorChallenge {

    public static void main(String[] args) {
        Random rand = new Random();

        ShoeWarehouse sw = new ShoeWarehouse();

        var producerExecutor = Executors.newSingleThreadExecutor();
        producerExecutor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                sw.receiveOrder(new Order(i, ShoeWarehouse.products[rand.nextInt(3)], rand.nextInt(1, 10)));
            }
        });
        producerExecutor.shutdown();

        var consumerExecutor = Executors.newSingleThreadExecutor();
        consumerExecutor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                sw.fulfillOrder();
            }
        });
        consumerExecutor.shutdown();
    }

}

class ShoeWarehouse {

    public final static String[] products = {"boots", "sneakers", "running shoes"};

    private List<Order> orders = new ArrayList<>();

    private static int ORDERS_MAX_AMOUNT = 3;

    synchronized void receiveOrder(Order order) {
        while (orders.size() > ORDERS_MAX_AMOUNT) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Order received! " + order);
        orders.add(order);
        notifyAll();
    }

    synchronized void fulfillOrder() {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order order = orders.remove(0);
        System.out.println("Fulfilled the order " + order);
        notifyAll();
    }

}

record Order(int id, String type, int quantity) {

}