import React from "react";

/**
 * GastoListView.jsx
 *
 * Componente de presentación que renderiza una tabla con la lista de gastos.
 * Recibe un array de objetos `gasto` como prop, y muestra concepto, monto y fecha.
 *
 * @param {Array} gastos - Lista de gastos con formato:
 *   {
 *     concepto: string,
 *     monto: number,
 *     fechaRegistro: string (ISO 8601)
 *   }
 */
const GastoListView = ({ gastos }) => {
  return (
    <table className="gasto-table">
      <thead>
        <tr>
          <th className="thConcepto">Concepto</th>
          <th className="thMonto">Monto</th>
          <th className="thFecha">Fecha</th>
        </tr>
      </thead>
      <tbody>
        {gastos.map((gasto, index) => (
          <tr key={index} className="border-t">
            <td className="tdConcepto">{gasto.concepto}</td>
            <td className="tdMonto">${gasto.monto.toFixed(2)}</td>
            <td className="tdFecha">
              {/* Formatea la fecha de registro a DD-MM-YYYY */}
              {(() => {
                const [year, month, day] = gasto.fechaRegistro.slice(0, 10).split("-");
                return `${day}-${month}-${year}`;
              })()}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default GastoListView;
