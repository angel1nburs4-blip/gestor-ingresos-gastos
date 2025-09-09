import React, { useState } from "react";
import axios from "axios";
import GastoFormView from "./GastoFormView";

/**
 * GastoForm.jsx
 *
 * Componente contenedor que gestiona el estado y la lógica de envío
 * del formulario de gastos. Se comunica con el backend vía POST
 * y delega la presentación a GastoFormView.
 */
const GastoForm = () => {
    // Estado local para los campos del formulario
    const [concepto, setConcepto] = useState('');
    const [monto, setMonto] = useState('');

    /**
     * Maneja el envío del formulario.
     * Envía los datos al backend y limpia los campos si la operación es exitosa.
     */
    const handleSubmit = (e) => {
        e.preventDefault();

        // Construye el objeto de gasto a enviar
        const newGasto = { concepto, monto };

        // Realiza la petición POST al backend
        axios.post("http://localhost:8080/api/gasto", newGasto)
            .then(response => {
                console.log("Spent registered:", response.data);
                setConcepto('');
                setMonto('');
            })
            .catch(error => {
                console.error("Error adding spent:", error);
            });
    };

    // Renderiza la vista del formulario, pasando props controladas
    return (
        <GastoFormView
            monto={monto}
            concepto={concepto}
            onMontoChange={(e) => setMonto(e.target.value)}
            onConceptoChange={(e) => setConcepto(e.target.value)}
            onSubmit={handleSubmit}
        />
    );
};

export default GastoForm;
