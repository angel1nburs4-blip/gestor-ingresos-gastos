package com.controlgastos.model;

import javax.persistence.*;

/**
 * Entidad que representa a un usuario del sistema.
 *
 * <p>
 * Contiene credenciales básicas: nombre de usuario único y contraseña.
 * </p>
 *
 * <p>
 * <strong>Notas de seguridad:</strong>
 * </p>
 * <ul>
 * <li>La contraseña debe almacenarse encriptada (no en texto plano).</li>
 * <li>El campo <code>username</code> es único para evitar duplicados.</li>
 * </ul>
 *
 * <p>
 * <strong>Uso típico:</strong> Autenticación, autorización y asociación con
 * registros financieros.
 * </p>
 */
@Entity
@Table(name = "users")
public class User {

    /** Identificador único del usuario (autogenerado). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre de usuario único para autenticación. */
    @Column(unique = true)
    private String username;

    /** Contraseña del usuario (debe estar encriptada antes de persistir). */
    private String password;

    // Getters y setters

    /** @return identificador único del usuario. */
    public Long getId() {
        return id;
    }

    /** @param id identificador único a asignar. */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return nombre de usuario. */
    public String getUsername() {
        return username;
    }

    /** @param username nombre de usuario a asignar (debe ser único). */
    public void setUsername(String username) {
        this.username = username;
    }

    /** @return contraseña del usuario. */
    public String getPassword() {
        return password;
    }

    /**
     * Asigna la contraseña del usuario.
     * <p>
     * <strong>Importante:</strong> debe encriptarse antes de guardar en base de
     * datos.
     * </p>
     * 
     * @param password contraseña en texto plano (solo para asignación inicial).
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
