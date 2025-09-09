package com.controlgastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.controlgastos.model.User;

/**
 * Repositorio para la entidad {@link User}.
 *
 * <p>Extiende {@link JpaRepository} para proporcionar operaciones CRUD estándar
 * sobre los usuarios registrados en el sistema.</p>
 *
 * <p><strong>Consultas personalizadas:</strong></p>
 * <ul>
 *   <li>{@code findByUsername(String username)}: busca un usuario por su nombre de usuario.</li>
 *   <li>{@code existsByUsername(String username)}: verifica si ya existe un usuario con ese nombre.</li>
 * </ul>
 *
 * <p><strong>Uso típico:</strong> Autenticación, validación de registro, y gestión de usuarios.</p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username nombre de usuario a buscar.
     * @return un {@link Optional} que contiene el usuario si existe.
     */
    Optional<User> findByUsername(String username);

    /**
     * Verifica si ya existe un usuario con el nombre de usuario especificado.
     *
     * @param username nombre de usuario a verificar.
     * @return {@code true} si el usuario existe, {@code false} en caso contrario.
     */
    boolean existsByUsername(String username);
}
