package com.controlgastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Clase principal de arranque para la aplicación Control de Gastos.
 *
 * <p>Esta clase inicia el contexto de Spring Boot y configura automáticamente
 * los componentes, servicios y controladores definidos en el proyecto.</p>
 *
 * <p>Extiende {@link SpringBootServletInitializer} para permitir despliegue
 * en contenedores servlet como Tomcat cuando se empaqueta como archivo WAR.</p>
 *
 * <p><strong>Uso:</strong> Ejecutar como aplicación Java estándar o desplegar en servidor web.</p>
 */
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

    /**
     * Método principal que lanza la aplicación Spring Boot.
     *
     * @param args argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
