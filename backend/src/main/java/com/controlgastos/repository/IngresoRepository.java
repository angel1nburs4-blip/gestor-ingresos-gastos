package com.controlgastos.repository;

import com.controlgastos.model.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio para la entidad {@link Ingreso}.
 *
 * <p>
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar
 * sobre los registros de ingresos.
 * </p>
 *
 * <p>
 * Incluye un método personalizado para obtener el ingreso más reciente
 * según el orden descendente del ID.
 * </p>
 *
 * <p>
 * <strong>Uso típico:</strong> Consultar ingresos, registrar nuevos ingresos,
 * y obtener el último ingreso registrado.
 * </p>
 *
 * <p>
 * Métodos heredados de {@code JpaRepository}:
 * </p>
 * <ul>
 * <li>{@code findAll()}</li>
 * <li>{@code findById(Long id)}</li>
 * <li>{@code save(Ingreso ingreso)}</li>
 * <li>{@code deleteById(Long id)}</li>
 * </ul>
 */
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

    /**
     * Obtiene el ingreso más reciente registrado en la base de datos.
     *
     * <p>
     * Ordena los ingresos por ID en orden descendente y devuelve el primero.
     * </p>
     *
     * @return un {@link Optional} que contiene el ingreso más reciente, si existe.
     */
    Optional<Ingreso> findTopByOrderByIdDesc();
}
