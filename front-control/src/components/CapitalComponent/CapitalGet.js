import React, { useEffect, useState } from "react";
import axios from "axios";
import CapitalView from "./CapitalView";

/**
 * Componente que consulta el capital actual desde el backend
 * y lo pasa como prop al componente de visualización CapitalView.
 *
 * <p>Al montar el componente, se realiza una petición GET a /api/capital
 * incluyendo el token JWT almacenado en localStorage.</p>
 */
function CapitalComponent() {
  // Estado local para almacenar el capital recibido
  const [capital, setCapital] = useState(null);

  useEffect(() => {
    // Recupera el token JWT desde localStorage (asumido que se guardó al hacer login)
    const token = localStorage.getItem("token");

    // Realiza la petición al backend con el token en el header Authorization
    axios
      .get("http://localhost:8080/api/capital", {
        headers: {
          Authorization: `Bearer ${token}`, // Autenticación vía JWT
        },
      })
      .then((response) => {
        // Actualiza el estado con el capital recibido
        setCapital(response.data);
      })
      .catch((error) => {
        // Manejo de errores: muestra en consola
        console.error("Error al obtener capital", error);
      });
  }, []); // Se ejecuta solo una vez al montar el componente

  // Renderiza el componente de vista, pasando el capital como prop
  return <CapitalView capital={capital} />;
}

export default CapitalComponent;
