package com.becksoft.meat.grill.entity;

import com.becksoft.meat.grill.enums.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName roleName;

    public Role(RoleName roleName){
        if (roleName == null) {
            throw new IllegalArgumentException("Role name cannot be null");
        }
        this.roleName = roleName;
    }

    public String role() {
        return roleName.toString();
    }

}
