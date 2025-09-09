package com.controlgastos.controller;

import com.controlgastos.model.Capital;
import com.controlgastos.repository.CapitalRepository;
import com.controlgastos.clasesCalculos.CalcularCapital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar operaciones relacionadas con el capital.
 *
 * <p>
 * Expone endpoints bajo la ruta <code>/api/capital</code> para consultar el
 * capital registrado.
 * </p>
 *
 * <p>
 * <strong>Flujo general:</strong>
 * </p>
 * <ol>
 * <li>El cliente (por ejemplo un frontend en React) hace una petición GET a
 * <code>/api/capital</code>.</li>
 * <li>El controlador delega la consulta en {@link CapitalRepository} para
 * obtener el registro más reciente de capital.</li>
 * <li>Si existe un registro, lo devuelve; si no, retorna
 * <code>null</code>.</li>
 * </ol>
 *
 * <p>
 * Permite solicitudes CORS desde <code>http://localhost:3000</code>, lo que
 * habilita la comunicación directa con el frontend en desarrollo.
 * </p>
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/capital")
public class CapitalColtroller {

    /**
     * Instancia local de la clase de cálculo de capital.
     * Actualmente no se usa en este controlador, pero está disponible
     * para futuras operaciones de cálculo antes de persistir o devolver datos.
     */
    private final CalcularCapital calculoCapital = new CalcularCapital();

    /** Repositorio JPA para acceder a datos de capital en la base. */
    @Autowired
    private CapitalRepository capitalRepository;

    /**
     * Obtiene el último capital registrado.
     *
     * <p>
     * <strong>Flujo:</strong>
     * </p>
     * <ol>
     * <li>Invoca {@link CapitalRepository#findTopByOrderByIdDesc()} para buscar el
     * registro más reciente por ID descendente.</li>
     * <li>Devuelve el objeto {@link Capital} encontrado o <code>null</code> si no
     * hay registros.</li>
     * </ol>
     *
     * @return el capital más reciente o null si no hay datos.
     */
    @GetMapping
    public Capital getCapital() {
        // Busca el último registro de capital según ID
        return capitalRepository.findTopByOrderByIdDesc()
                .orElse(null); // Si no encuentra ninguno, devuelve null
    }
}
