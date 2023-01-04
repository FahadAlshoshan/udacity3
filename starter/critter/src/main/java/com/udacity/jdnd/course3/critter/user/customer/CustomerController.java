package com.udacity.jdnd.course3.critter.user.customer;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/customer")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return CustomerMapper.INSTANCE.toDTO(service.saveCustomer(CustomerMapper.INSTANCE.toEntity(customerDTO)));
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return CustomerMapper.INSTANCE.toDTOList(service.getAllCustomers());
    }

    @GetMapping("/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return CustomerMapper.INSTANCE.toDTO(service.getCustomerByPetId(petId));
    }
}
