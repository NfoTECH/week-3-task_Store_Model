package dev.nfotech;

import dev.nfotech.model.Cashier;
import dev.nfotech.model.Customer;
import dev.nfotech.model.Product;
import dev.nfotech.model.Store;

import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Store.readFile();
        Customer jackson = new Customer("Jackson", 1000);
        Customer richard= new Customer("Richard", 1200);
        Customer aliyah = new Customer("Aliyah", 2150);


        Cashier cashier= new Cashier();
        jackson.addToCart(new Product("Potato Chips", 12));
        jackson.addToCart(new Product("Oatmeal Raisin", 5));
        jackson.addToCart(new Product("Carrot", 10));
        jackson.addToCart(new Product("Arrowroot", 8));
        jackson.addToCart(new Product("Chocolate Chip", 17));
        jackson.addToCart(new Product("Biscuit", 17));


        richard.addToCart(new Product("Carrot", 12));
        richard.addToCart(new Product("Arrowroot", 10));
        richard.addToCart(new Product("Chocolate Chip", 9));
        richard.addToCart(new Product("Banana", 15));
        richard.addToCart(new Product("Potato Chips", 14));


        aliyah.addToCart(new Product("Arrowroot", 19));
        aliyah.addToCart(new Product("Pretzels", 11));
        aliyah.addToCart(new Product("Carrot", 7));
        aliyah.addToCart(new Product("Whole Wheat", 22));
        aliyah.addToCart(new Product("Oatmeal Raisin", 22));


        PriorityQueue<Customer> customers = new PriorityQueue<>(new Customer());
        Collections.addAll(customers, jackson, richard, aliyah);

        System.out.println("\nSales made according to the QUANTITY of items on the cart");
        while (!customers.isEmpty()) {
            Customer cus = customers.poll();
            System.out.println("Total items in " + cus.getName() + " cart is " + cus.getNoOfItemCart() + ".");

            System.out.println(customers.size());
        }

        cashier.makeSale(aliyah, Product.stock);
        cashier.makeSale(richard, Product.stock);
        cashier.makeSale(jackson, Product.stock);
    }
}
