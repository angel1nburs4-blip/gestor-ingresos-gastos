package com.controlgastos.controller;

import com.controlgastos.model.Ingreso;
import com.controlgastos.model.Capital;
import com.controlgastos.repository.CapitalRepository;
import com.controlgastos.repository.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controlador REST para la gestión de ingresos.
 *
 * <p>
 * Expone endpoints bajo <code>/api/ingreso</code> para:
 * <ul>
 * <li>Consultar el último ingreso registrado.</li>
 * <li>Registrar un nuevo ingreso y actualizar el capital.</li>
 * </ul>
 *
 * <p>
 * <strong>Flujo general:</strong>
 * </p>
 * <ol>
 * <li>En <strong>GET /api/ingreso</strong>:
 * <ul>
 * <li>Consulta el ingreso más reciente en la base de datos.</li>
 * <li>Devuelve el objeto {@link Ingreso} o <code>null</code> si no hay
 * registros.</li>
 * </ul>
 * </li>
 * <li>En <strong>POST /api/ingreso</strong>:
 * <ul>
 * <li>Valida y guarda el nuevo ingreso.</li>
 * <li>Obtiene el capital más reciente o crea uno nuevo si no existe.</li>
 * <li>Suma el monto del ingreso al capital actual.</li>
 * <li>Guarda el nuevo capital actualizado.</li>
 * <li>Devuelve el ingreso registrado en la respuesta.</li>
 * </ul>
 * </li>
 * </ol>
 *
 * <p>
 * Permite solicitudes CORS desde <code>http://localhost:3000</code> para
 * habilitar interacción con el frontend local.
 * </p>
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/ingreso")
public class IngresoController {

    /** Repositorio JPA para operaciones sobre la entidad Ingreso. */
    @Autowired
    private IngresoRepository ingresoRepository;

    /** Repositorio JPA para operaciones sobre la entidad Capital. */
    @Autowired
    private CapitalRepository capitalRepository;

    /**
     * Obtiene el último ingreso registrado.
     *
     * @return objeto {@link Ingreso} más reciente o <code>null</code> si no hay
     *         registros.
     */
    @GetMapping
    public Ingreso getIngreso() {
        // Busca el ingreso más reciente por ID descendente
        return ingresoRepository.findTopByOrderByIdDesc().orElse(null);
    }

    /**
     * Registra un nuevo ingreso y actualiza el capital en base de datos.
     *
     * <p>
     * <strong>Flujo:</strong>
     * </p>
     * <ol>
     * <li>Guarda el ingreso recibido en la base de datos.</li>
     * <li>Recupera el capital más reciente; si no existe, crea uno nuevo.</li>
     * <li>Suma el monto del ingreso al capital actual.</li>
     * <li>Guarda el nuevo valor de capital.</li>
     * <li>Devuelve el ingreso registrado en la respuesta HTTP.</li>
     * </ol>
     *
     * @param ingreso objeto {@link Ingreso} recibido en la solicitud (validado).
     * @return respuesta HTTP con el ingreso registrado.
     */
    @PostMapping
    public ResponseEntity<Ingreso> createIngreso(@Valid @RequestBody Ingreso ingreso) {
        // Guardar ingreso en la base de datos
        Ingreso savedIngreso = ingresoRepository.save(ingreso);

        // Obtener último capital registrado o crear uno nuevo si no existe
        Capital capital = capitalRepository.findTopByOrderByIdDesc().orElse(null);
        if (capital == null) {
            capital = new Capital(); // Capital inicial en 0
        }

        // Actualizar capital sumando el monto del ingreso
        capital.setCapital(capital.getCapital() + ingreso.getMonto());
        capitalRepository.save(capital);

        // Devolver ingreso registrado
        return ResponseEntity.ok(ingreso);
    }
}