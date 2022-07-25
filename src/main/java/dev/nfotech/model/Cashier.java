package dev.nfotech.model;

import dev.nfotech.enums.Role;

import java.util.*;

public class Cashier {

    ArrayList<Integer> toRemove = new ArrayList<>();//the indexes of items that are not in stock or unavailable
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    double totalPrice = 0;
    public void makeSale(Customer customer, HashMap<String, Product> stock) {
        ArrayList<Product> cart = customer.getMyCart();
        for (Product prod : cart) {
            String key = prod.getName();
            if (stock.containsKey(key)) {
                if (stock.get(key).getQuantity() == 0) {
                    System.out.println(key + " is out of stock");
                    toRemove.add(cart.indexOf(prod));
                } else {
                    if (stock.get(key).getQuantity() >= prod.getQuantity()) {
                        totalPrice += prod.getQuantity() * stock.get(key).getPrice();
                    }
                }
            } else {
                System.out.println("Sorry " + key + " is not available");
                toRemove.add(cart.indexOf(prod));
            }
        }
        completeTransactionAndIssueReceipt(customer, stock);
    }

    public void sellByProductPriority (Product product) {
        Queue<Customer>generalQueue = product.getGeneralQueue();
        for (Customer customer : generalQueue) {
            ArrayList<Product> cart = customer.getMyCart();
            Map<String, PriorityQueue<CustomerDTO>> priorityQueueMap = Product.getProductQueue();
            PriorityQueue<CustomerDTO> customerDTOS = priorityQueueMap.get(product.getName());
            while (!customerDTOS.isEmpty()) {
                HashMap<String, Product> stock = Product.getStock();
                CustomerDTO customerDTO = customerDTOS.peek();
                completeTransactionAndIssueReceipt(customer, stock);
                System.out.println(customerDTO.getQuantity() + " " + customerDTO.getCustomerName() + " sold to " + customerDTO.getCustomerName());
                customerDTOS.poll();
            }
        }
    }



    // If customer have enough cash, Complete transaction and issue receipt
    public void completeTransactionAndIssueReceipt(Customer customer, HashMap<String, Product> stock) {
        ArrayList<Product> cart = customer.getMyCart();
        for (int i = toRemove.size() - 1; i >= 0; i--) {  //Remove items either not available  or out of stock
            cart.remove(cart.get(toRemove.get(i)));       //remove by index didn't work, so is employed remove by getting the object by that index
        }
        if (customer.getWallet() >= totalPrice) {
            double totalPrice = 0;
            System.out.println("\n**********************\nCustomer name: " + customer.getName());
            for (Product prod : cart) {
                String key = prod.getName();
                stock.get(key).setQuantity(stock.get(key).getQuantity() - prod.getQuantity());
                totalPrice += prod.getQuantity() * stock.get(key).getPrice();
                System.out.println("**********************" + "\nItem purchased: " + key +
                        "\nUnit price: " + stock.get(key).getPrice() +
                        "\nQuantity purchased: " + prod.getQuantity() +
                        "\nQuantity price: " + Math.round((prod.getQuantity() * stock.get(key).getPrice())*100.0)/100.0);
            }
            System.out.println("-------------------------\nTotal price : "+
                    Math.round((totalPrice)*100.0)/100.0 + "\n-------------------------");
            System.out.println("Successful Transaction\n"+
                    "Thanks for your patronage\n");
        } else {
            System.out.println("Insufficient fund\n");
        }
    }
}
