package com.udacity.jdnd.course3.critter.user.employee;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Enumerated;
import lombok.Data;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
@Data
public class EmployeeRequestDTO {

    private Set<EmployeeSkill> skills;
    private LocalDate date;

}
