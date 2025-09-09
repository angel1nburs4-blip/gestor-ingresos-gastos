/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        primary: '#001f3f',        // Azul marino institucional
        primaryLight: '#334E68', // Variante para hover/fondo sobre primary
        secondary: '#0074D9',      // Azul brillante tipo inbursa
        secondaryLight: '#60A5FA', // Azul claro para estados pasivos
        background: '#F4F6F8',     // Fondo de la app
        surface: '#FFFFFF',        // Tarjetas, modales, inputs, forms
        divider: '#E5E7EB',        // Bordes y líneas sutiles
        text: '#1F2937',           // Texto principal
        textMuted: '#6B7280',     // Texto secundario
        textInverse: '#F4F6F8',   // Para fondos oscuros
        success: '#10B981',        // Éxito
        successLight: '#D1FAE5',  // Fondo verde suave
        error: '#EF4444',          // Error
        errorLight: '#FEE2E2',    // Fondo rojo suave
        accent: '#FFD700',         // Dorado para íconos o CTA
      },
    },
  },
  plugins: [],
}