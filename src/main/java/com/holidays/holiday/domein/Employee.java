package com.holidays.holiday.domein;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Employee {
    @Id
    @Generated
    private Long id;
    private String lastname;
    private String firstname;
    private String patronymic;

}
