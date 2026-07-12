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
    private PlatoSelected platoSeleccionado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_option", nullable = false)
    private GuarnicionOption guarnicionseleccionada;

    public GuarnicionSelected(GuarnicionOption guarnicionseleccionada, PlatoSelected platoseleccionado) {
        assingGuarnicion(guarnicionseleccionada);
        assingPlatoSelected(platoseleccionado);
        send();
    }

    private void assingGuarnicion(GuarnicionOption guarnicionseleccionada) {
        if (guarnicionseleccionada == null) {
            throw new IllegalArgumentException("guarnicion cannot be null");
        }
        this.guarnicionseleccionada = guarnicionseleccionada;
    }

    private void assingPlatoSelected(PlatoSelected platoSeleccionado) {
        if (platoSeleccionado == null) {
            throw new IllegalArgumentException("plato cannot be null");
        }
        this.platoSeleccionado = platoSeleccionado;
    }

    private void send() {
        this.platoSeleccionado.addGuarnicion(this);
    }

    private void removeGuarnicion() {
        this.platoSeleccionado.removeGuarnicion(this);
    }

}
