package com.controlgastos.clasesCalculos;

/**
 * Realiza operaciones básicas sobre capital (sumar ingresos y restar gastos)
 * y mantiene el último resultado en memoria.
 *
 * <p>
 * Nota: Para operaciones monetarias se recomienda considerar BigDecimal
 * para evitar problemas de precisión con double.
 * </p>
 */
public class CalcularCapital {

    /** Último capital calculado y almacenado en esta instancia. */
    private double capitalActual;

    /**
     * Suma un ingreso a un capital base y guarda el resultado en
     * {@code capitalActual}.
     *
     * @param capital capital base sobre el que se realizará la suma
     * @param ingreso monto a sumar (puede ser negativo si se desea permitir
     *                ajustes)
     */
    public void sumarCapital(double capital, double ingreso) {
        this.capitalActual = capital + ingreso;
    }

    /**
     * Resta un gasto de un capital base y guarda el resultado en
     * {@code capitalActual}.
     *
     * @param capital capital base sobre el que se realizará la resta
     * @param gasto   monto a restar (puede ser negativo si se desea permitir
     *                ajustes)
     */
    public void restarCapital(double capital, double gasto) {
        this.capitalActual = capital - gasto;
    }

    /**
     * Devuelve el último valor de capital calculado y almacenado.
     *
     * @return el valor actual de {@code capitalActual}
     */
    public double regresarCapital() {
        return capitalActual;
    }
}
