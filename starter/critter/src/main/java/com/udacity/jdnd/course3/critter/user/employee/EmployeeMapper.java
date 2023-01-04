package com.udacity.jdnd.course3.critter.user.employee;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO toDTO(EmployeeEntity entity);

    EmployeeEntity toEntity(EmployeeDTO dto);

    List<EmployeeDTO> toDTOList(List<EmployeeEntity> entity);

    List<EmployeeEntity> toEntityList(List<EmployeeDTO> dto);
}
