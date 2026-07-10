package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee")
    private Employee employee;
    @Column(nullable = false, unique = true, length = 25)
    private String username;
    @Column(nullable = false, length = 25)
    private String password;
    private boolean active = true;

    public User(Role role, Employee employee, String username, String password) {
        assingRole(role);
        assingEmployee(employee);
        changeUsername(username);
        changePassword(password);
    }

    public void fire() {
        this.active = false;
    }

    public void hire() {
        this.active = true;
    }

    private void assingRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = role;
    }

    private void assingEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        this.employee = employee;
    }

    private void changeUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username;
    }

    private void changePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

}
