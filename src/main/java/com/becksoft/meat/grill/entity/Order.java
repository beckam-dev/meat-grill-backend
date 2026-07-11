package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;
    @Enumerated(EnumType.STRING)
    @Column(name = "modalidad", nullable = false)
    private OrderType orderType;
    @Column(name = "nombre_cliente", nullable = true)
    private String nombreCliente;
    @ManyToMany
    @JoinTable(
            name = "orders_mesas",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_mesas")
    )
    private List<Mesa> mesas;
    @Column(name = "fecha_order")
    private LocalDateTime fechaOrder;
    @Column(name = "status")
    private OrderStatus status = OrderStatus.PENDIENTE;
    @Column(name = "total_amount")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    public Order(Employee employee, OrderType orderType, String nombreCliente, List<Mesa> mesas) {
        assignEmployee(employee);
        assignModalidad(orderType);
        assingClient(nombreCliente);
        assignMesa(mesas);
        now();
    }

    private void assignEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        this.employee = employee;
    }

    private void assignModalidad(OrderType orderType) {
        if (orderType == null) {
            throw new IllegalArgumentException("Order type cannot be null.");
        }
        this.orderType = orderType;
    }

    private void assingClient(String nombreCliente) {
        if (nombreCliente == null || nombreCliente.isEmpty()) {
            nombreCliente = "N/A";
        }
        this.nombreCliente = nombreCliente;
    }

    private void assignMesa(List<Mesa> mesas) {
        if (mesas == null) {
            throw new IllegalArgumentException("Mesa(s) cannot be null.");
        }
        this.mesas = mesas;
    }

    private void now() {
        this.fechaOrder =  LocalDateTime.now();
    }

    public void changeStatus(OrderStatus orderStatus) {
        if (orderStatus == null) {
            throw new IllegalArgumentException("Order status cannot be null.");
        }
        this.status = orderStatus;
    }

    public void modifyTotalAmount(BigDecimal totalAmount) {
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total amount cannot be null or negative.");
        }
        this.totalAmount = totalAmount;
    }

    public void aumentarTotalAmount(BigDecimal precio) {
        if (precio == null) {
            throw new IllegalArgumentException("Precio cannot be null.");
        }
        this.totalAmount = this.totalAmount.add(precio);
    }

    public void disminuirTotalAmount(BigDecimal precio) {
        if (precio == null) {
            throw new IllegalArgumentException("Precio cannot be null.");
        }
        this.totalAmount = this.totalAmount.subtract(precio);
    }

}
