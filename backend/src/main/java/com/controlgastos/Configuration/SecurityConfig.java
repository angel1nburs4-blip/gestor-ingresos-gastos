package com.controlgastos.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración global de seguridad HTTP para la aplicación.
 *
 * <p>
 * <strong>Flujo al arrancar la app:</strong>
 * </p>
 * <ol>
 * <li>Spring detecta esta clase por la anotación {@code @Configuration}.</li>
 * <li>Se invoca el método {@code securityFilterChain} para construir la
 * cadena de filtros de seguridad.</li>
 * <li>Se aplican las reglas definidas (permitir todas las solicitudes,
 * desactivar CSRF, permitir iframes).</li>
 * <li>La cadena de filtros resultante se registra como bean y se aplica a
 * todas las peticiones HTTP.</li>
 * </ol>
 *
 * <p>
 * <strong>Advertencia:</strong> Esta configuración es insegura para entornos
 * de producción, ya que no requiere autenticación ni protege contra ataques
 * CSRF.
 * Úsese únicamente en desarrollo o pruebas.
 * </p>
 */
@Configuration
public class SecurityConfig {

    /**
     * Define la configuración de seguridad HTTP mediante un
     * {@link SecurityFilterChain}.
     * <p>
     * Esta implementación:
     * <ul>
     * <li>Permite el acceso a cualquier ruta sin autenticación.</li>
     * <li>Desactiva la protección CSRF (Cross-Site Request Forgery).</li>
     * <li>Deshabilita las restricciones para cargar la aplicación en iframes.</li>
     * </ul>
     * </p>
     *
     * @param http objeto de configuración {@link HttpSecurity} que proporciona
     *             una API fluida para establecer reglas de seguridad.
     * @return la cadena de filtros de seguridad construida.
     * @throws Exception si ocurre un error al configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 1. Configura autorización: cualquier request está permitida
        http.authorizeRequests(auth -> auth
                .anyRequest().permitAll())

                // 2. Desactiva protección CSRF (no recomendado en producción)
                .csrf(csrf -> csrf.disable())

                // 3. Permite que la aplicación se embeba en iframes
                // Útil en desarrollo para herramientas como H2 Console
                .headers(headers -> headers.frameOptions().disable());

        // 4. Construye y devuelve la configuración final
        return http.build();
    }
}
