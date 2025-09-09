import React, { useState } from "react";
import axios from "axios";
import IngresoFormView from "./IngresoFormView";

/**
 * IngresoForm.jsx
 *
 * Componente contenedor que gestiona el estado del formulario de ingreso
 * y realiza la petición POST al backend para registrar un nuevo ingreso.
 *
 * <p>Al enviar el formulario, se construye un objeto `newIngreso` con los datos
 * del estado local y se envía a /api/ingreso.</p>
 */
const IngresoForm = () => {
  // Estado local para los campos del formulario
  const [concepto, setConcepto] = useState('');
  const [monto, setMonto] = useState('');

  /**
   * Maneja el envío del formulario.
   * Envía los datos al backend y limpia el formulario si la operación es exitosa.
   *
   * @param {Event} e - Evento de envío del formulario
   */
  const handleSubmit = (e) => {
    e.preventDefault();

    const newIngreso = { concepto, monto };

    axios.post("http://localhost:8080/api/ingreso", newIngreso)
      .then(response => {
        console.log('Ingreso registrado', response.data);
        setConcepto('');
        setMonto('');
      })
      .catch(error => {
        console.error('Error al registrar ingreso', error);
      });
  };

  // Renderiza la vista del formulario, pasando props controladas
  return (
    <IngresoFormView
      monto={monto}
      concepto={concepto}
      onMontoChange={(e) => setMonto(e.target.value)}
      onConceptoChange={(e) => setConcepto(e.target.value)}
      onSubmit={handleSubmit}
    />
  );
};

export default IngresoForm;
