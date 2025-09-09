package com.controlgastos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.controlgastos.model.User;
import com.controlgastos.services.AuthService;
import com.controlgastos.services.JwtUtil;

/**
 * Controlador REST encargado de gestionar operaciones de autenticación.
 * 
 * <p>
 * Expone endpoints bajo la ruta base <strong>/auth</strong> para:
 * <ul>
 * <li>Iniciar sesión y obtener un token JWT.</li>
 * <li>Registrar nuevos usuarios.</li>
 * </ul>
 *
 * <p>
 * <strong>Flujo general:</strong>
 * </p>
 * <ol>
 * <li>El cliente (ej. frontend React en localhost:3000) envía una petición a
 * /auth/login o /auth/register.</li>
 * <li>El controlador recibe la petición, mapea el cuerpo a un objeto
 * {@code User} y delega la lógica en {@link AuthService}.</li>
 * <li>En el caso de login exitoso, se genera un JWT con {@link JwtUtil} y se
 * devuelve al cliente.</li>
 * <li>En caso de error (credenciales inválidas o usuario duplicado), se
 * devuelve un código HTTP correspondiente (401 o 400).</li>
 * </ol>
 *
 * <p>
 * Permite solicitudes CORS desde <code>http://localhost:3000</code>, útil para
 * el desarrollo con frontend separado.
 * </p>
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    /** Utilidad para generar y validar tokens JWT. */
    private final JwtUtil jwtUtil;

    /** Servicio que maneja la lógica de autenticación y registro de usuarios. */
    private final AuthService authService;

    /**
     * Constructor que inyecta las dependencias necesarias.
     *
     * @param jwtUtil     utilidad para manejo de JWT.
     * @param authService servicio con la lógica de autenticación/registro.
     */
    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    /**
     * Endpoint para iniciar sesión.
     * 
     * <p>
     * <strong>Flujo:</strong>
     * </p>
     * <ol>
     * <li>Recibe un objeto {@code User} con username y password.</li>
     * <li>Valida credenciales usando {@link AuthService#validatUser}.</li>
     * <li>Si son válidas:
     * <ul>
     * <li>Genera un JWT para el usuario.</li>
     * <li>Construye respuesta JSON con token y username.</li>
     * <li>Retorna 200 OK.</li>
     * </ul>
     * </li>
     * <li>Si no son válidas, retorna 401 Unauthorized.</li>
     * </ol>
     *
     * @param loginRequest datos de inicio de sesión (username, password).
     * @return respuesta con token y username o mensaje de error.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        // Validar credenciales
        if (authService.validatUser(loginRequest.getUsername(), loginRequest.getPassword())) {
            // Generar token JWT
            String token = jwtUtil.generateToken(loginRequest.getUsername());

            // Construir respuesta JSON
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", loginRequest.getUsername());

            return ResponseEntity.ok(response);
        }
        // Credenciales inválidas
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales Inválias");
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     * 
     * <p>
     * <strong>Flujo:</strong>
     * </p>
     * <ol>
     * <li>Recibe un objeto {@code User} con username y password.</li>
     * <li>Intenta registrarlo mediante {@link AuthService#register}.</li>
     * <li>Si el registro es exitoso:
     * <ul>
     * <li>Devuelve 200 OK con mensaje "Registro exitoso".</li>
     * </ul>
     * </li>
     * <li>Si el username ya existe:
     * <ul>
     * <li>Devuelve 400 Bad Request con mensaje de error.</li>
     * </ul>
     * </li>
     * </ol>
     *
     * @param userRequest datos del usuario a registrar.
     * @return mensaje de éxito o error según el caso.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userRequest) {
        boolean success = authService.register(userRequest.getUsername(), userRequest.getPassword());
        if (success) {
            return ResponseEntity.ok("Registro exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario ya existe");
        }
    }

}
