package com.udacity.jdnd.course3.critter.user.customer;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
        return repository.save(customerEntity);
    }

    public List<CustomerEntity> getAllCustomers() {
        return (List<CustomerEntity>) repository.findAll();
    }

    public CustomerEntity getCustomerByPetId(Long petId) {
        return repository.findByPetId(petId);
    }
}
