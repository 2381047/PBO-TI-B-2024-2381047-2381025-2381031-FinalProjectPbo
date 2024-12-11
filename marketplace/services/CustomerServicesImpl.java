package Marketplace.services;

import Marketplace.entities.Customer;
import Marketplace.repositories.CustomerRepository;

import java.util.ArrayList;

public class CustomerServicesImpl implements CustomerServices {
    private CustomerRepository customerRepository;

    public CustomerServicesImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void registerCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.delete(id);
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerDetails(int id) {
        return customerRepository.findById(id);
    }
}