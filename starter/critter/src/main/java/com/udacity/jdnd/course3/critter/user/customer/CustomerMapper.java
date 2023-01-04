package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Named("petsToPetIds")
    static List<Long> petsToPetIds(List<PetEntity> pets) {
        List<Long> ids = new ArrayList<>();
        if (pets == null || pets.isEmpty()) return null;
        for (PetEntity pet : pets) ids.add(pet.getId());
        return ids;
    }

    @Mapping(target = "petIds", source = "pets", qualifiedByName = "petsToPetIds")
    CustomerDTO toDTO(CustomerEntity entity);

    CustomerEntity toEntity(CustomerDTO dto);

    List<CustomerDTO> toDTOList(List<CustomerEntity> entity);

    List<CustomerEntity> toEntityList(List<CustomerDTO> dto);
}
