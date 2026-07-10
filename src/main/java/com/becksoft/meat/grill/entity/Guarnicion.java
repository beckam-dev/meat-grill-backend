package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "guarniciones")
public class Guarnicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guarnicion")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "group_name", nullable = false)
    private GuarnicionGroup category;

    public Guarnicion(GuarnicionGroup group) {
        assignGroup(group);
    }

    private void assignGroup(GuarnicionGroup group) {
        if (group == null) {
            throw new NullPointerException("Group cannot be null.");
        }
        this.category = group;
    }

}
