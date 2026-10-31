import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// The proxy avoids CORS: the browser talks to :5173/api, Vite forwards to Spring Boot on :8085.
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: { '/api': { target: 'http://localhost:8085', changeOrigin: true, rewrite: p => p.replace(/^\/api/, '') } },
  },
});
