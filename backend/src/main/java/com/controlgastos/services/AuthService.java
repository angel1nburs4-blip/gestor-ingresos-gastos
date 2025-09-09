package com.controlgastos.services;

import org.springframework.stereotype.Service;
import com.controlgastos.model.User;
import com.controlgastos.repository.UserRepository;

import java.util.Optional;

/**
 * Servicio de autenticación y registro de usuarios.
 *
 * <p>Proporciona lógica para validar credenciales y registrar nuevos usuarios
 * en el sistema de control de gastos.</p>
 *
 * <p><strong>Advertencia:</strong> Esta implementación compara contraseñas en texto plano.
 * Se recomienda aplicar cifrado (e.g., BCrypt) para producción.</p>
 */
@Service
public class AuthService {

    private final UserRepository userRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param userRepository repositorio de usuarios.
     */
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Valida si el usuario existe y si la contraseña coincide.
     *
     * @param username nombre de usuario.
     * @param password contraseña en texto plano.
     * @return {@code true} si las credenciales son válidas, {@code false} en caso contrario.
     */
    public boolean validatUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    /**
     * Registra un nuevo usuario si el nombre de usuario no está en uso.
     *
     * @param username nombre de usuario deseado.
     * @param password contraseña en texto plano.
     * @return {@code true} si el registro fue exitoso, {@code false} si el usuario ya existe.
     */
    public boolean register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // ⚠️ Recomendado: aplicar hash antes de guardar
        userRepository.save(user);
        return true;
    }
}
