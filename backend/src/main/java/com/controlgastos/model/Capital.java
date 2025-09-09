package com.controlgastos.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa el capital acumulado en el sistema.
 *
 * <p>
 * Cada instancia refleja el estado del capital en un momento específico,
 * registrado automáticamente al persistir en base de datos.
 * </p>
 *
 * <p>
 * <strong>Campos:</strong>
 * </p>
 * <ul>
 * <li><strong>id</strong>: Identificador único autogenerado.</li>
 * <li><strong>capital</strong>: Monto total acumulado.</li>
 * <li><strong>fechaRegistro</strong>: Fecha y hora en que se registró el
 * capital (no modificable).</li>
 * </ul>
 *
 * <p>
 * <strong>Uso típico:</strong> Se actualiza cada vez que se registra un nuevo
 * ingreso.
 * </p>
 */
@Entity
public class Capital {

    /** Identificador único del registro de capital. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Monto total de capital acumulado. */
    private double capital;

    /**
     * Fecha y hora en que se registró el capital.
     * <p>
     * Se asigna automáticamente antes de persistir y no puede ser modificada.
     * </p>
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    /**
     * Asigna automáticamente la fecha de registro antes de persistir en base de
     * datos.
     * <p>
     * Este método se ejecuta justo antes de que el objeto sea guardado.
     * </p>
     */
    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y setters

    /** @return identificador único del capital. */
    public Long getId() {
        return id;
    }

    /** @param id identificador único a asignar. */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return monto actual de capital. */
    public double getCapital() {
        return capital;
    }

    /**
     * Asigna el monto de capital.
     * 
     * @param capital nuevo valor de capital acumulado.
     */
    public void setCapital(Double capital) {
        this.capital = capital;
    }

    /** @return fecha en que se registró el capital. */
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Asigna manualmente la fecha de registro (no recomendado).
     * <p>
     * Este método existe por compatibilidad, pero <strong>no debe usarse</strong>
     * fuera de pruebas o migraciones.
     * </p>
     * 
     * @param fechaRegistro fecha a asignar.
     */
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
