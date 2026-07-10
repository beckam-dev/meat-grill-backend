package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long id;
    @Column(nullable = false, unique = true, length = 15)
    private String dni;
    @Column(nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    public Employee(String dni, String name, String lastName, String phoneNumber) {
        assignDNI(dni);
        assignName(name);
        assignLastName(lastName);
        changePhoneNumber(phoneNumber);
    }

    public void assignDNI(String dni) {
        if (dni.isEmpty() || dni.length() > 15) {
            throw new IllegalArgumentException("DNI has to be defined or it is longer than 14 characters");
        }
        this.dni = dni;
    }

    public void assignName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name has to be defined or it is empty");
        }
        this.name = name;
    }

    public void assignLastName(String lastName) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name has to be defined or it is empty");
        }
        this.lastName = lastName;
    }

    public void changePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || phoneNumber.length() > 11) {
            throw new IllegalArgumentException("Phone number has to be defined or it is empty or longer than 11 characters");
        }
        this.phoneNumber = phoneNumber;
    }

}
