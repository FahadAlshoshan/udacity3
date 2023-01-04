package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PetMapper {
    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    @Mapping(target = "ownerId",source = "owner.id")
    PetDTO toDTO(PetEntity entity);
    @Mapping(target = "owner.id",source = "ownerId")

    PetEntity toEntity(PetDTO dto);

    List<PetDTO> toDTOList(List<PetEntity> entity);

    List<PetEntity> toEntityList(List<PetDTO> dto);
}
