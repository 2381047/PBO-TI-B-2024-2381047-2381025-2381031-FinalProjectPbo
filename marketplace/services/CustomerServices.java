package Marketplace.services;

import Marketplace.entities.Customer;

import java.util.ArrayList;

public interface CustomerServices {
    void registerCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
    ArrayList<Customer> getAllCustomers();
    Customer getCustomerDetails(int id);
}