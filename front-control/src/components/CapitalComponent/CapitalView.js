/**
 * CapitalView.jsx
 * 
 * Componente de presentación que muestra el capital actual.
 * Recibe el objeto `capital` como prop y renderiza su valor si está disponible.
 * Si no, muestra un mensaje de carga.
 */

const CapitalView = ({ capital }) => (
  <div className="capitalActual">
    <h2 className="h2">Capital Actual</h2>

    {/* Renderiza el capital si está disponible, o muestra "Cargando..." */}
    {capital ? (
      <p className="textOK">${capital.capital}</p>
    ) : (
      <p className="textWait">Cargando...</p>
    )}
  </div>
);

export default CapitalView;
