package com.controlgastos.clasesCalculos;

/**
 * Acumula el total de gastos registrados.
 * 
 * <p>
 * Esta clase permite sumar nuevos gastos al total actual
 * y obtener el monto acumulado. No realiza validación de entrada
 * ni control de precisión monetaria.
 * </p>
 */
public class SumaGasto {

    /** Monto total de gastos acumulados. */
    private double sumaGastos = 0;

    /**
     * Suma un gasto al acumulador.
     *
     * @param gasto monto a añadir al total (puede ser negativo si se permiten
     *              ajustes)
     */
    public void sumarGastos(double gasto) {
        this.sumaGastos += gasto;
    }

    /**
     * Devuelve el total acumulado de gastos.
     *
     * @return suma total de gastos registrados
     */
    public double obtenerSumaGastos() {
        return sumaGastos;
    }
}
