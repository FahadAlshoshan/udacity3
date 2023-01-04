package com.udacity.jdnd.course3.critter.user.employee;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeDTO saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return EmployeeMapper.INSTANCE.toDTO(service.saveEmployee(EmployeeMapper.INSTANCE.toEntity(employeeDTO)));
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return EmployeeMapper.INSTANCE.toDTO(service.getEmployee(employeeId));
    }

    @PutMapping("/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        service.updateEmployeeAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return EmployeeMapper.INSTANCE.toDTOList(service.getEmployeeBySkillsAndAvailability(
            employeeDTO.getSkills(), employeeDTO.getDate().getDayOfWeek()));
    }
}
