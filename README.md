# 💰 ControlGastos App

Aplicación full-stack para gestionar ingresos, gastos y visualizar el capital disponible. Desarrollada con **React**, **Spring Boot** y autenticación **JWT**, con enfoque en experiencia premium, validación visual y estructura profesional.

---

## 🛠️ Tecnologías utilizadas

### Frontend
- React
- Axios
- Tailwind CSS
- React Router

### Backend
- Spring Boot
- JWT (JSON Web Token)
- REST API
- CORS habilitado para desarrollo local

### Base de datos
- H2 (modo desarrollo)
- Oracle (modo producción opcional)

---

## 🚀 Funcionalidades principales

- ✅ Registro y login de usuarios con validación de credenciales
- ✅ Generación de token JWT y persistencia en localStorage
- ✅ Registro de ingresos y gastos con formularios controlados
- ✅ Visualización del capital actual calculado dinámicamente
- ✅ Experiencia visual optimizada con Tailwind y componentes reutilizables
- ✅ Separación clara entre lógica y presentación (componentes `Form`, `View`, `Controller`)

---

## 📦 Estructura del proyecto

control-gastos-app/ 
├── backend/
│ ├── src/main/java/com/controlgastos/controller/ 
│ ├── src/main/java/com/controlgastos/services/ 
│ ├── src/main/java/com/controlgastos/model/ 
│ └── src/main/resources/application.properties 
├── frontend/ 
│ ├── src/components/ 
│ ├── src/pages/ 
│ ├── src/App.jsx 
│ └── src/index.js 
├── README.md 
├── .gitignore


---

## 📡 Endpoints principales (Backend)

| Método | Ruta             | Descripción                          |
|--------|------------------|--------------------------------------|
| POST   | `/auth/login`    | Autenticación de usuario             |
| POST   | `/auth/register` | Registro de nuevo usuario            |
| POST   | `/api/gasto`     | Registro de gasto                    |
| POST   | `/api/ingreso`   | Registro de ingreso                  |
| GET    | `/api/capital`   | Obtención del capital actual         |

---

## 🧪 Cómo ejecutar el proyecto

### 🔧 Backend (Spring Boot)
```bash
cd backend
./mvnw spring-boot:run

Asegúrate de tener Java 17+ y Maven instalado. Puedes usar H2 por defecto o configurar Oracle en application.properties.

### 🎨 Frontend (React)
```bash
cd frontend
npm install
npm start
La app se ejecutará en http://localhost:3000 y se conectará al backend en http://localhost:8080.

## 👨‍💻 Autor

**Angel Teran** – Full-stack Developer  
📍 CDMX, México  
