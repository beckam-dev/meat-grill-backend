package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "guarniciones_selected")
public class GuarnicionSelected {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guarnicion_selected")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plato_selected")
    private PlatoSelected platoSelected;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_option", nullable = false)
    private GuarnicionOption guarnicionSeleccionada;

    public GuarnicionSelected(GuarnicionOption guarnicionseleccionada, PlatoSelected platoseleccionado) {
        assignGuarnicion(guarnicionseleccionada);
        assignPlatoSelected(platoseleccionado);
        send();
    }

    private void assignGuarnicion(GuarnicionOption guarnicionseleccionada) {
        if (guarnicionseleccionada == null) {
            throw new IllegalArgumentException("guarnicion cannot be null");
        }
        this.guarnicionSeleccionada = guarnicionseleccionada;
    }

    private void assignPlatoSelected(PlatoSelected platoSeleccionado) {
        if (platoSeleccionado == null) {
            throw new IllegalArgumentException("plato cannot be null");
        }
        this.platoSelected = platoSeleccionado;
    }

    private void send() {
        this.platoSelected.addGuarnicion(this);
    }

    private void removeGuarnicion() {
        this.platoSelected.removeGuarnicion(this);
    }

}
