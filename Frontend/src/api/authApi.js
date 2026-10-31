// AuthController  /Safe
import { request } from './http.js';

/** POST /Safe/login — returns { token } */
export const login = (userName, password) => request('POST', '/Safe/login', { body: { userName, password } });
