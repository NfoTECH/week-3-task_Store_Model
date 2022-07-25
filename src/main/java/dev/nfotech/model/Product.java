package dev.nfotech.model;

import java.util.*;

public class Product {

    private String name;
    private String category;
    private int quantity;
    private double price;


    public static HashMap<String, Product> stock = new HashMap<>();

    public Queue<Customer> generalQueue = new LinkedList<>();
    public static Map<String, PriorityQueue<CustomerDTO>> productQueue = new HashMap<>();

    public Product(String name, String category, int quantity, double price) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public static Map<String, PriorityQueue<CustomerDTO>> getProductQueue() {
        return productQueue;
    }

    public static HashMap<String, Product> getStock() {
        return stock;
    }

    public Queue<Customer> getGeneralQueue() {
        return generalQueue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String toString(){
        return name+" "+category+" "+quantity+" "+price;
    }

    public String customersJoinTheGeneralQueue(Customer customer) {
        String message;
        this.generalQueue.add(customer);
        message = "customer added to the queue successfully";
        System.out.println(customer.getName() + " just joined the general queue");
        for (Product product: customer.getMyCart()) {
            if (productQueue.containsKey(product.getName())) {
                PriorityQueue<CustomerDTO> productPriorityQueue = productQueue.get(product.getName());
                CustomerDTO customerDTO = new CustomerDTO(customer.getName(), product.getName(), product.getQuantity());
                productPriorityQueue.add(customerDTO);
            }
            else {
                CustomerDTO customerDTO = new CustomerDTO(customer.getName(), product.getName(), product.getQuantity());
                PriorityQueue<CustomerDTO> productPriorityQueue = new PriorityQueue<>();
                productPriorityQueue.add(customerDTO);
                productQueue.put(product.getName(), productPriorityQueue);
            }
            message = "customer successfully added to " + product.getName() + " queue";
        }
        return message;
    }
}
