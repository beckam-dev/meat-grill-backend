package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "guarniciones_options")
public class GuarnicionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_option")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guarnicion", nullable = false)
    private Guarnicion guarnicion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insumo", nullable = false)
    private Insumo insumo;

    public GuarnicionOption(Guarnicion guarnicion, Insumo insumo){
        assingGuarnicion(guarnicion);
        assingInsumo(insumo);
    }

    private void assingGuarnicion(Guarnicion guarnicion) {
        if (guarnicion == null){
            throw new IllegalArgumentException("Guarnicion cannot be null.");
        }
        this.guarnicion = guarnicion;
    }

    private void assingInsumo(Insumo insumo) {
        if (insumo == null){
            throw new IllegalArgumentException("Insumo cannot be null.");
        }
        this.insumo = insumo;
    }

}
