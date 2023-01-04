package com.udacity.jdnd.course3.critter.user.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    @Query(value = "SELECT c FROM Customer c JOIN c.pets p where p.id = :petId")
    CustomerEntity findByPetId(@Param("petId") Long petId);
}
