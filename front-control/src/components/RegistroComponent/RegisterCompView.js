/**
 * RegisterCompView.jsx
 *
 * Componente de presentación para el formulario de registro de usuario.
 * Recibe props controladas desde el componente contenedor `Register`.
 *
 * Props:
 * - userName: string — valor actual del campo "Usuario"
 * - password: string — valor actual del campo "Contraseña"
 * - onUserNameChange: function — handler para cambios en el campo "Usuario"
 * - onPasswordChange: function — handler para cambios en el campo "Contraseña"
 * - onSubmit: function — handler para el envío del formulario
 * - message: string | null — mensaje de éxito o error tras el intento de registro
 * - setShowRegister: function — permite volver al formulario de login
 */

const RegisterCompView = ({
    userName,
    password,
    onUserNameChange,
    onPasswordChange,
    onSubmit,
    message,
    setShowRegister
}) => (
    <div className="bgContainerLoginRegister">
        <form onSubmit={onSubmit} className="form">
            <h2 className="h2">Registro</h2>

            {/* Campo de usuario */}
            <input
                id="userName"
                type="text"
                placeholder="Usuario"
                value={userName}
                onChange={onUserNameChange}
                required
            />

            {/* Campo de contraseña */}
            <input
                id="password"
                type="password"
                placeholder="Contraseña"
                value={password}
                onChange={onPasswordChange}
                required
            />

            {/* Botón de envío */}
            <button type="submit" className="btn-submit">
                Registrar
            </button>

            {/* Mensaje de éxito o error */}
            {message && (
                <p className="textOK">{message}</p>
            )}
        </form>

        {/* Botón para volver al login */}
        <button
            onClick={() => setShowRegister(false)}
            className="btn-submit mt-4"
        >
            ¿Ya tienes cuenta? Inicia sesión
        </button>
    </div>
);

export default RegisterCompView;
