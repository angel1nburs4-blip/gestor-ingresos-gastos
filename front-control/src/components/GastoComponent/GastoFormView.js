/**
 * GastoFormView.jsx
 *
 * Componente de presentación para el formulario de registro de gastos.
 * Recibe props controladas desde GastoForm y delega la lógica al componente contenedor.
 *
 * Props:
 * - monto: string | número — valor del campo "Monto"
 * - concepto: string — valor del campo "Concepto"
 * - onMontoChange: función — handler para cambios en el campo "Monto"
 * - onConceptoChange: función — handler para cambios en el campo "Concepto"
 * - onSubmit: función — handler para el envío del formulario
 */
const GastoFormView = ({ monto, concepto, onMontoChange, onConceptoChange, onSubmit }) => (
  <form onSubmit={onSubmit} className="gasto-form">
    <h2 className="h2">Registrar Gasto</h2>

    {/* Campo para el monto del gasto */}
    <input
      type="number"
      placeholder="Monto"
      value={monto}
      onChange={onMontoChange}
      className="input-monto"
      required
      min="0"
      step="0.01"
    />

    {/* Campo para el concepto del gasto */}
    <input
      type="text"
      placeholder="Concepto"
      value={concepto}
      onChange={onConceptoChange}
      className="input-concepto"
      required
    />

    {/* Botón para enviar el formulario */}
    <button type="submit" className="btn-submit">
      Registrar
    </button>
  </form>
);

export default GastoFormView;
