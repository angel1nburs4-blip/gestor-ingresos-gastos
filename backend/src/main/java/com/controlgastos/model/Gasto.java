package com.controlgastos.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * Entidad que representa un gasto registrado por el usuario.
 *
 * <p>
 * Cada gasto contiene un concepto, un monto positivo y una fecha de registro
 * que se asigna automáticamente al persistir.
 * </p>
 *
 * <p>
 * <strong>Validaciones:</strong>
 * </p>
 * <ul>
 * <li><strong>concepto</strong>: no puede estar vacío y tiene un máximo de 25
 * caracteres.</li>
 * <li><strong>monto</strong>: debe ser mayor a 0.</li>
 * </ul>
 *
 * <p>
 * <strong>Uso típico:</strong> Se registra desde el frontend y se descuenta del
 * capital actual.
 * </p>
 */
@Entity
public class Gasto {

    /** Identificador único del gasto (autogenerado). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Descripción del gasto. No puede estar vacía ni exceder 25 caracteres. */
    @NotEmpty(message = "El campo no puede estar vacío")
    @Size(max = 25, message = "El concepto no puede tener más de 25 caracteres")
    private String concepto;

    /** Monto del gasto. Debe ser mayor a cero. */
    @Positive(message = "El monto debe ser mayor a 0")
    private double monto;

    /**
     * Fecha y hora en que se registró el gasto.
     * <p>
     * Se asigna automáticamente antes de persistir y no puede modificarse.
     * </p>
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    /**
     * Asigna automáticamente la fecha de registro antes de guardar en base de
     * datos.
     */
    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y setters

    /** @return identificador único del gasto. */
    public Long getId() {
        return id;
    }

    /** @param id identificador único a asignar. */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return concepto o descripción del gasto. */
    public String getConcepto() {
        return concepto;
    }

    /** @param concepto descripción del gasto. */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /** @return monto del gasto. */
    public double getMonto() {
        return monto;
    }

    /** @param monto valor del gasto (debe ser positivo). */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /** @return fecha en que se registró el gasto. */
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
