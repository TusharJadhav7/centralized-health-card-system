// UserController  /user
import { request } from './http.js';

/** POST /user/signUpNew — register a new user */
export const signUp = data => request('POST', '/user/signUpNew', { body: data });

/** GET /user/GetPersonalInfo/{userId} — currently blocked by SecurityConfig, kept for later */
export const getUserById = userId => request('GET', `/user/GetPersonalInfo/${encodeURIComponent(userId)}`);
