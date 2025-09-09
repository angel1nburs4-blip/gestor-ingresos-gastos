import React, { useState } from 'react';
import axios from 'axios';
import LoginFormView from './LoginFormView';

/**
 * LoginForm.jsx
 *
 * Componente contenedor que gestiona el estado del formulario de login
 * y realiza la autenticación contra el backend.
 *
 * Este componente encapsula la lógica de autenticación:
 * - Captura credenciales del usuario
 * - Envía una petición POST al endpoint /auth/login
 * - Si la autenticación es exitosa, guarda el token y actualiza el estado global
 * - Si falla, muestra un mensaje de error
 * 
 * Además, permite alternar entre la vista de login y registro mediante `setShowRegister`.
 *
 * Props:
 * @param {function} setToken - Función para actualizar el token en el estado global tras login exitoso
 * @param {function} setShowRegister - Función para alternar entre login y registro
 * @param {boolean} showRegister - Estado actual de la vista (true = registro, false = login)
 */
const LoginForm = ({ setToken, setShowRegister, showRegister }) => {
  // Estado local para los campos del formulario
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  /**
   * handleSubmit
   *
   * Función que se ejecuta al enviar el formulario.
   * Realiza una petición POST al backend con las credenciales ingresadas.
   * Si el login es exitoso, guarda el token en localStorage y actualiza el estado global.
   * Si ocurre un error, muestra un mensaje de alerta.
   *
   * @param {React.FormEvent<HTMLFormElement>} e - Evento de envío del formulario
   */
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      console.log("Enviando login:", username, password);

      const { data } = await axios.post(
        'http://localhost:8080/auth/login',
        { username, password }
      );

      // Guardar token en estado global y localStorage
      setToken(data);
      localStorage.setItem("token", data);

      alert("Login successful");
    } catch (error) {
      console.error("Error en login:", error);
      alert("Invalid username or password");
    }
  };

  /**
   * Renderiza el componente de vista `LoginFormView`,
   * pasando los valores y handlers necesarios como props controladas.
   */
  return (
    <LoginFormView
      username={username}
      password={password}
      onUserNameChange={(e) => setUsername(e.target.value)}
      onPasswordChange={(e) => setPassword(e.target.value)}
      onSubmit={handleSubmit}
      setShowRegister={setShowRegister}
      showRegister={showRegister}
    />
  );
};

export default LoginForm;
