package com.controlgastos.repository;

import com.controlgastos.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad {@link Gasto}.
 *
 * <p>
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar
 * sobre los registros de gastos.
 * </p>
 *
 * <p>
 * <strong>Uso típico:</strong> Consultar, registrar y eliminar gastos desde el
 * backend.
 * </p>
 *
 * <p>
 * Al heredar de {@code JpaRepository}, incluye métodos como:
 * </p>
 * <ul>
 * <li>{@code findAll()}</li>
 * <li>{@code findById(Long id)}</li>
 * <li>{@code save(Gasto gasto)}</li>
 * <li>{@code deleteById(Long id)}</li>
 * </ul>
 */
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    // Puedes agregar métodos personalizados aquí si necesitas filtros por fecha,
    // usuario, etc.
}
