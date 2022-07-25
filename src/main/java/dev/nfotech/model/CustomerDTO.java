package dev.nfotech.model;

public class CustomerDTO implements Comparable<CustomerDTO> {
    String customerName;
    String productName;
    int quantity;

    public CustomerDTO(String customerName,String productName, int quantity) {
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }


    @Override
    public int compareTo(CustomerDTO o) {
        return this.getQuantity() > o.getQuantity() ? -1: 1;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerName='" + customerName + '\'' +
                ", productName=" + productName +
                ", quantity=" + quantity +
                '}';
    }
}