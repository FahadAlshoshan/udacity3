package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeEntity;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Named("petsToPetIds")
    static List<Long> petsToPetIds(List<PetEntity> pets) {
        List<Long> ids = new ArrayList<>();
        if (pets == null || pets.isEmpty()) return null;
        for (PetEntity pet : pets) ids.add(pet.getId());
        return ids;
    }

    @Named("employeeToEmployeeIds")
    static List<Long> employeeToEmployeeIds(List<EmployeeEntity> employees) {
        List<Long> ids = new ArrayList<>();
        if (employees == null || employees.isEmpty()) return null;
        for (EmployeeEntity employee : employees) ids.add(employee.getId());
        return ids;
    }

    @Mapping(target = "petIds", source = "pets", qualifiedByName = "petsToPetIds")
    @Mapping(target = "employeeIds", source = "employees", qualifiedByName = "employeeToEmployeeIds")
    ScheduleDTO toDTO(ScheduleEntity entity);

    ScheduleEntity toEntity(ScheduleDTO dto);

    List<ScheduleDTO> toDTOList(List<ScheduleEntity> entity);

    List<ScheduleEntity> toEntityList(List<ScheduleDTO> dto);
}
