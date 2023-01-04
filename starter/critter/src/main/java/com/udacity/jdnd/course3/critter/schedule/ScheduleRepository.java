package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<ScheduleEntity, Long> {

  @Query(value = "SELECT s FROM Schedule s JOIN s.pets p WHERE p.id = :PetId")
  List<ScheduleEntity> findByPetId(@Param("PetId") Long petId);

  @Query(value = "SELECT s FROM Schedule s JOIN s.employees e WHERE e.id = :EmployeeId")
  List<ScheduleEntity> findByEmployeeId(@Param("EmployeeId") Long employeeId);

  @Query(value = "SELECT s FROM Schedule s JOIN s.pets p WHERE p.owner.id = :CustomerId")
  List<ScheduleEntity> findByCustomerId(@Param("CustomerId") Long customerId);
}
