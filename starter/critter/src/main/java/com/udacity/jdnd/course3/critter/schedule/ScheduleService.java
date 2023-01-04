package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository repository;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;

    public ScheduleService(
            ScheduleRepository repository, PetRepository petRepository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
    }

    public ScheduleEntity saveSchedule(ScheduleEntity schedule, List<Long> employeeIds, List<Long> petIds) {
        List<EmployeeEntity> employees = employeeIds.stream()
                .map(id -> employeeRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Employee was not found.")))
                .collect(Collectors.toList());

        List<PetEntity> pets = petIds.stream()
            .map(id -> petRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet was not found.")))
            .collect(Collectors.toList());

        schedule.setPets(pets);
        schedule.setEmployees(employees);
        return repository.save(schedule);
    }

    public List<ScheduleEntity> getAllSchedules() {
        return (List<ScheduleEntity>) repository.findAll();
    }

    public List<ScheduleEntity> getScheduleByPetId(Long petId) {
        return repository.findByPetId(petId);
    }

    public List<ScheduleEntity> getScheduleByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    public List<ScheduleEntity> getScheduleByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }
}
