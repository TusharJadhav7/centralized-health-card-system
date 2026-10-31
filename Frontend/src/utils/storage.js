export const ROLES = { ROLE_PATIENT: 'Patient', ROLE_DOCTOR: 'Doctor', ROLE_CHEMIST: 'Chemist' };
export const load = (k, d) => { try { return JSON.parse(localStorage.getItem(k)) ?? d; } catch { return d; } };
export const save = (k, v) => localStorage.setItem(k, JSON.stringify(v));
export const decode = t => {
  try { return JSON.parse(atob(t.split('.')[1].replace(/-/g, '+').replace(/_/g, '/'))); } catch { return {}; }
};
export const dash = v => (v === null || v === undefined || v === '' ? '—' : v);
