package com.udacity.jdnd.course3.critter.user.employee;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        return repository.save(employeeEntity);
    }

    public EmployeeEntity getEmployee(Long employeeId) {
        return repository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException("Employee was not found"));
    }

    @Transactional
    public void updateEmployeeAvailability(Set<DayOfWeek> availability, Long employeeId) {
        EmployeeEntity employeeFromDB = getEmployee(employeeId);
        employeeFromDB.setDaysAvailable(availability);
        saveEmployee(employeeFromDB);
    }

    public List<EmployeeEntity> getEmployeeBySkillsAndAvailability(Set<EmployeeSkill> skills, DayOfWeek day) {
        return repository.findBySkillsInAndDaysAvailableIn(day).stream()
                .filter(employeeEntity -> employeeEntity.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }
}
