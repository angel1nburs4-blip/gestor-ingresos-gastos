/**
 * App.jsx
 *
 * Componente raíz de la aplicación de control financiero.
 * Gestiona la autenticación del usuario y define las rutas principales mediante React Router.
 *
 * Flujo general:
 * - Si el usuario no está autenticado (token === null), se muestra el formulario de login o registro.
 * - Si el usuario está autenticado, se habilita la navegación y se renderizan las vistas protegidas.
 *
 * Estado:
 * - token: string | null — token JWT recibido tras login, usado para proteger rutas.
 * - showRegister: boolean — controla si se muestra el formulario de registro o login.
 */

import './App.css'; // Estilos globales de la aplicación

import React, { useState } from "react"; // Hook para manejar estado local

// Componentes de React Router para navegación SPA
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';

// Componentes funcionales que representan cada vista
import CapitalGet from './components/CapitalComponent/CapitalGet';
import GastoList from './components/GastoComponent/GastoList';
import GastoForm from './components/GastoComponent/GastoForm';
import IngresoForm from './components/IngresoComponent/IngresoForm';
import LoginForm from './components/LoginComponent/LoginForm';
import RegisterCompView from './components/RegistroComponent/RegisterComponent';

const App = () => {
  const [token, setToken] = useState(null); // Token de autenticación
  const [showRegister, setShowRegister] = useState(false); // Controla vista de registro

  // 🔐 Vista pública: login o registro
  if (!token) {
    return (
      <div className="bgContainerLoginRegister">
        {showRegister ? (
          <RegisterCompView setShowRegister={setShowRegister} />
        ) : (
          <LoginForm
            setToken={setToken}
            setShowRegister={setShowRegister}
            showRegister={showRegister}
          />
        )}
      </div>
    );
  }

  // 🔐 Vista protegida: navegación y rutas internas
  return (
    <Router>
      <div>
        {/* 🧭 Barra de navegación principal */}
        <nav className="bar" aria-label="Menú principal">
          <ul className="flex justify-around">
            <li><Link to="/gastoForm">Registrar Gasto</Link></li>
            <li><Link to="/ingresoForm">Registrar Ingreso</Link></li>
            <li><Link to="/gastoList">Lista de Gastos</Link></li>
            <li><Link to="/capital">Capital</Link></li>
            {/* 🔄 Puedes agregar aquí un botón de logout o ruta /login si lo necesitas */}
          </ul>
        </nav>

        {/* 📦 Contenedor principal del contenido según la ruta */}
        <main role="main" className="container mx-auto p-8">
          <Routes>
            <Route path="/gastoForm" element={<GastoForm />} />
            <Route path="/ingresoForm" element={<IngresoForm />} />
            <Route path="/gastoList" element={<GastoList />} />
            <Route path="/capital" element={<CapitalGet />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
};

export default App;
