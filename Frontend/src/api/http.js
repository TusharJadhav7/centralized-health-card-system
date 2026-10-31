// Shared request helper (same style as the SMT api.js). Every module file uses it.
// In dev, Vite proxies /api -> http://localhost:8085. For a direct URL set VITE_API_BASE.
export const API_BASE = import.meta.env.VITE_API_BASE || '/api';

export async function request(method, path, { body = null, token = null, params = null } = {}) {
  const qs = params ? '?' + new URLSearchParams(params).toString() : '';
  const opts = { method, headers: { ...(body ? { 'Content-Type': 'application/json' } : {}), ...(token ? { Authorization: `Bearer ${token}` } : {}) } };
  if (body) opts.body = JSON.stringify(body);

  let res;
  try { res = await fetch(`${API_BASE}${path}${qs}`, opts); }
  catch { throw Object.assign(new Error('Cannot reach the server. Start the backend on port 8085 and try again.'), { status: 0 }); }

  const text = await res.text();
  let data = text;
  try { data = text ? JSON.parse(text) : null; } catch { /* plain text reply */ }

  if (!res.ok) {
    const msg = res.status === 401 || res.status === 403
      ? 'You are not allowed to do this. Log in again or check your role.'
      : Array.isArray(data) ? data.join('. ') : data?.msg || data?.message || (typeof data === 'string' && data) || `Request failed (${res.status})`;
    throw Object.assign(new Error(msg), { status: res.status });
  }
  return data;
}
