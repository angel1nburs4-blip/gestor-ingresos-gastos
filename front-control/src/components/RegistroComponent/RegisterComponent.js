import { useState } from "react";
import axios from "axios";
import RegisterCompView from "./RegisterCompView";

/**
 * Register.jsx
 *
 * Componente contenedor que gestiona el estado del formulario de registro
 * y realiza la petición POST al backend para crear un nuevo usuario.
 *
 * Este componente encapsula la lógica de interacción con el servidor,
 * maneja el estado local de los campos del formulario y delega la presentación
 * a `RegisterCompView`, manteniendo una separación clara entre lógica y vista.
 *
 * Props:
 * - setShowRegister: function — permite alternar entre la vista de registro y login.
 *
 * Estado:
 * - username: string — valor actual del campo "Usuario".
 * - password: string — valor actual del campo "Contraseña".
 * - message: string | null — mensaje de éxito o error tras el intento de registro.
 */
const Register = ({ setShowRegister }) => {
    // Estado local para los campos del formulario
    const [username, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState(null);

    /**
     * handleSubmit
     *
     * Función que se ejecuta al enviar el formulario.
     * Realiza una petición POST al endpoint /auth/register con los datos ingresados.
     * Si el registro es exitoso, muestra un mensaje y redirige al login tras 1.5 segundos.
     * Si ocurre un error, muestra el mensaje de error recibido desde el backend.
     *
     * @param {React.FormEvent<HTMLFormElement>} e - Evento de envío del formulario.
     */
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // Envío de datos al backend
            await axios.post("http://localhost:8080/auth/register", {
                username,
                password,
            });

            // Mensaje de éxito y redirección al login
            setMessage("Registro exitoso");

            setTimeout(() => {
                setMessage(null);
                setShowRegister(false); // Cambia la vista a login
            }, 1500);

            // Limpieza de campos
            setUserName("");
            setPassword("");
        } catch (err) {
            // Captura de errores del backend
            setMessage(err.response?.data || "Error al registrar");
        }
    };

    /**
     * Renderiza el componente de vista `RegisterCompView`,
     * pasando los valores y handlers necesarios como props controladas.
     */
    return (
        <RegisterCompView
            userName={username}
            password={password}
            onUserNameChange={(e) => setUserName(e.target.value)}
            onPasswordChange={(e) => setPassword(e.target.value)}
            onSubmit={handleSubmit}
            message={message}
            setShowRegister={setShowRegister}
        />
    );
};

export default Register;
