package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.CustomerEntity;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Pet")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private PetType type;

    @Nationalized
    private String name;

    private LocalDate birthDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    private CustomerEntity owner;


    @Override
    public int hashCode() {
        return Objects.hash(type, name, birthDate, notes, owner);
    }
}
