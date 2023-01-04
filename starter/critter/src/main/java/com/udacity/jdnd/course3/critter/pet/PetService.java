package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.customer.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetService {
    private final PetRepository repository;
    private final CustomerRepository customerRepository;

    public PetService(PetRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public PetEntity savePet(PetEntity petEntity) {
        CustomerEntity customerFromDB = customerRepository
                .findById(petEntity.getOwner().getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        if (customerFromDB.getPets() == null || customerFromDB.getPets().isEmpty()) {
            customerFromDB.setPets(new ArrayList<>(List.of(petEntity)));
        } else {
            List<PetEntity> list = customerFromDB.getPets();
            list.add(petEntity);
            customerFromDB.setPets(list);
        }
        petEntity.setOwner(customerFromDB);
        CustomerEntity result = customerRepository.save(customerFromDB);
        return result.getPets().get(result.getPets().size() - 1);
    }

    public PetEntity getPet(Long petId) {
        return repository.findById(petId).orElseThrow(() -> new EntityNotFoundException("Pet was not found"));
    }

    public List<PetEntity> getAllPets() {
        return (List<PetEntity>) repository.findAll();
    }

    public List<PetEntity> getPetsByOwnerId(Long ownerId) {
        return repository.findByOwnerId(ownerId);
    }
}
