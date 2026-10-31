# CHC Frontend (React + Vite)

1. Start the Spring Boot backend (port 8085, MySQL `CHC`).
2. `npm install` then `npm run dev`, open http://localhost:5173
Requests go to `/api/*` and Vite proxies them to http://localhost:8085 (no CORS setup needed in dev).
Doctors must sign up with role Doctor (stored as `ROLE_DOCTOR`) so `hasRole("DOCTOR")` passes.
