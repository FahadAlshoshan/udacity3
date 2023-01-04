package com.udacity.jdnd.course3.critter.user.employee;

import java.time.DayOfWeek;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    @Query(value = "SELECT e FROM Employee e JOIN e.daysAvailable a " + "WHERE a IN :RequestedDay")
    List<EmployeeEntity> findBySkillsInAndDaysAvailableIn(@Param("RequestedDay") DayOfWeek day);
}