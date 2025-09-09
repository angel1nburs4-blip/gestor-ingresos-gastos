package com.controlgastos.repository;

import com.controlgastos.model.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio para la entidad {@link Capital}.
 *
 * <p>
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar.
 * </p>
 *
 * <p>
 * <strong>Consultas personalizadas:</strong>
 * </p>
 * <ul>
 * <li>{@code findTopByOrderByIdDesc()}: obtiene el último registro de capital
 * según el ID.</li>
 * </ul>
 *
 * <p>
 * <strong>Uso típico:</strong> Consultar el capital más reciente para cálculos
 * financieros o visualización en dashboard.
 * </p>
 */
public interface CapitalRepository extends JpaRepository<Capital, Long> {

    /**
     * Obtiene el último registro de capital según el orden descendente del ID.
     *
     * @return un {@link Optional} que contiene el capital más reciente, si existe.
     */
    Optional<Capital> findTopByOrderByIdDesc();
}
