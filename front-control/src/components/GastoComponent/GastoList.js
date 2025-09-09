import React, { useState, useEffect } from "react";
import axios from "axios";
import GastoListView from "./GastoListView";

/**
 * GastoList.jsx
 *
 * Componente contenedor que se encarga de obtener la lista de gastos desde el backend
 * y pasarla como prop al componente de presentación GastoListView.
 *
 * <p>Al montar el componente, realiza una petición GET a /api/gasto
 * para recuperar todos los gastos registrados.</p>
 */
const GastoList = () => {
    // Estado local para almacenar la lista de gastos
    const [gastos, setGastos] = useState([]);

    useEffect(() => {
        // Realiza la petición al backend para obtener los gastos
        axios
            .get("http://localhost:8080/api/gasto")
            .then((response) => {
                setGastos(response.data); // Actualiza el estado con los datos recibidos
            })
            .catch((error) => {
                console.error("Error fetching bills: ", error); // Manejo de errores
            });
    }, []); // Se ejecuta solo una vez al montar el componente

    // Renderiza la vista, pasando la lista de gastos como prop
    return <GastoListView gastos={gastos} />;
};

export default GastoList;
