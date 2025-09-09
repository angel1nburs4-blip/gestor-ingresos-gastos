/**
 * LoginFormView.jsx
 *
 * Componente de presentación para el formulario de login.
 * Recibe props controladas desde el componente contenedor `LoginForm`.
 *
 * Props:
 * - username: string — valor actual del campo "Usuario"
 * - password: string — valor actual del campo "Contraseña"
 * - onUserNameChange: function — handler para cambios en el campo "Usuario"
 * - onPasswordChange: function — handler para cambios en el campo "Contraseña"
 * - onSubmit: function — handler para el envío del formulario
 * - setShowRegister: function — alterna entre vista de login y registro
 * - showRegister: boolean — indica si actualmente se muestra la vista de registro
 */

const LoginFormView = ({
    username,
    password,
    onUserNameChange,
    onPasswordChange,
    onSubmit,
    setShowRegister,
    showRegister
}) => (
    <div className="bgContainerLoginRegister">
        <form onSubmit={onSubmit} className="form">
            <h2 className="h2">Login</h2>

            {/* Campo de usuario */}
            <input
                id="username"
                type="text"
                placeholder="Usuario"
                value={username}
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
                Ingresar
            </button>
        </form>

        {/* Botón para alternar entre login y registro */}
        <button
            onClick={() => setShowRegister(!showRegister)}
            className="btn-submit mt-4"
        >
            {showRegister
                ? "¿Ya tienes cuenta? Inicia sesión"
                : "¿No tienes cuenta? Regístrate"}
        </button>
    </div>
);

export default LoginFormView;
