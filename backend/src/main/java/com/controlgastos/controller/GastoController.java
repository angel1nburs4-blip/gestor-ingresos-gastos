package com.controlgastos.controller;

import com.controlgastos.model.Gasto;
import com.controlgastos.model.Capital;
import com.controlgastos.repository.CapitalRepository;
import com.controlgastos.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.Valid;
import com.controlgastos.clasesCalculos.SumaGasto;

/**
 * Controlador REST para la gestión de gastos.
 *
 * <p>
 * Expone endpoints bajo <code>/api/gasto</code> para:
 * <ul>
 * <li>Consultar todos los gastos registrados.</li>
 * <li>Registrar un nuevo gasto y actualizar el capital.</li>
 * </ul>
 *
 * <p>
 * <strong>Flujo general:</strong>
 * </p>
 * <ol>
 * <li>En <strong>GET /api/gasto</strong>:
 * <ul>
 * <li>Consulta todos los registros de gasto en la base de datos.</li>
 * <li>Devuelve la lista completa.</li>
 * </ul>
 * </li>
 * <li>En <strong>POST /api/gasto</strong>:
 * <ul>
 * <li>Valida y guarda el nuevo gasto.</li>
 * <li>Obtiene el capital más reciente o crea uno nuevo si no existe.</li>
 * <li>Resta el monto del gasto al capital actual y lo guarda.</li>
 * <li>Actualiza el acumulador en memoria usando {@link SumaGasto}.</li>
 * <li>Devuelve el gasto recién registrado en la respuesta.</li>
 * </ul>
 * </li>
 * </ol>
 *
 * <p>
 * Permite solicitudes CORS desde <code>http://localhost:3000</code> para
 * habilitar interacción con un frontend local.
 * </p>
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/gasto")
public class GastoController {

    /** Acumulador en memoria para el total de gastos. */
    private final SumaGasto sumaGastos = new SumaGasto();

    /** Repositorio JPA para operaciones sobre la entidad Gasto. */
    @Autowired
    private GastoRepository gastoRepository;

    /** Repositorio JPA para operaciones sobre la entidad Capital. */
    @Autowired
    private CapitalRepository capitalRepository;

    /**
     * Obtiene todos los gastos registrados.
     *
     * @return lista completa de objetos {@link Gasto}.
     */
    @GetMapping
    public List<Gasto> getAllGastos() {
        // Consulta todos los gastos desde la base de datos
        return gastoRepository.findAll();
    }

    /**
     * Registra un nuevo gasto y actualiza el capital en base de datos.
     *
     * <p>
     * <strong>Flujo:</strong>
     * </p>
     * <ol>
     * <li>Guarda el gasto recibido en la base de datos.</li>
     * <li>Recupera el capital más reciente; si no existe, crea uno nuevo.</li>
     * <li>Resta el monto del gasto al capital actual.</li>
     * <li>Guarda la nueva cifra de capital.</li>
     * <li>Suma el gasto al acumulador en memoria {@link SumaGasto}.</li>
     * <li>Imprime en consola el total acumulado (solo a modo de depuración).</li>
     * <li>Devuelve el gasto registrado en la respuesta HTTP con código 200 OK.</li>
     * </ol>
     *
     * @param gasto objeto {@link Gasto} recibido en la solicitud (validado por
     *              anotaciones).
     * @return respuesta HTTP con el gasto registrado.
     */
    @PostMapping
    public ResponseEntity<Gasto> createGasto(@Valid @RequestBody Gasto gasto) {
        // Guardar gasto en la base de datos
        Gasto savedGasto = gastoRepository.save(gasto);

        // Obtener último capital registrado o crear uno nuevo si no existe
        Capital capital = capitalRepository.findTopByOrderByIdDesc().orElse(null);
        if (capital == null) {
            capital = new Capital();
        }

        // Actualizar capital restando el monto del gasto
        capital.setCapital(capital.getCapital() - gasto.getMonto());
        capitalRepository.save(capital);

        // Actualizar acumulador en memoria
        sumaGastos.sumarGastos(savedGasto.getMonto());
        sumaGastos.obtenerSumaGastos();

        // Imprimir total acumulado en consola (para depuración)
        System.out.println(sumaGastos.obtenerSumaGastos());

        // Devolver gasto registrado
        return ResponseEntity.ok(savedGasto);
    }
}
