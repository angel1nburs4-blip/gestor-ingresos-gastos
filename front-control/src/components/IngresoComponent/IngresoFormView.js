import React from "react";

/**
 * IngresoFormView.jsx
 *
 * Componente de presentación para el formulario de ingreso.
 * Recibe props controladas desde el componente contenedor `IngresoForm`.
 *
 * @param {string} monto - Valor actual del campo monto
 * @param {string} concepto - Valor actual del campo concepto
 * @param {function} onMontoChange - Handler para cambios en el campo monto
 * @param {function} onConceptoChange - Handler para cambios en el campo concepto
 * @param {function} onSubmit - Handler para el envío del formulario
 */
const IngresoFormView = ({ monto, concepto, onMontoChange, onConceptoChange, onSubmit }) => (
  <form onSubmit={onSubmit} className="ingreso-form">
    <h2 className="h2">Registrar Ingreso</h2>

    <input
      type="number"
      placeholder="Monto"
      value={monto}
      onChange={onMontoChange}
      className="input-monto"
      min="0"
      step="0.01"
      required
    />

    <input
      type="text"
      placeholder="Concepto"
      value={concepto}
      onChange={onConceptoChange}
      className="input-concepto"
      maxLength="100"
      required
    />

    <button type="submit" className="btn-submit">
      Registrar
    </button>
  </form>
);

export default IngresoFormView;
