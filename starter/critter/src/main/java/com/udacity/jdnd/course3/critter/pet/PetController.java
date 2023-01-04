package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return PetMapper.INSTANCE.toDTO(service.savePet(PetMapper.INSTANCE.toEntity(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return PetMapper.INSTANCE.toDTO(service.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return PetMapper.INSTANCE.toDTOList(service.getAllPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
        return PetMapper.INSTANCE.toDTOList(service.getPetsByOwnerId(ownerId));
    }
}
