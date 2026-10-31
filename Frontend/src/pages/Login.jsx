import { useState } from 'react';
import { login } from '../api';
import Field from '../components/Field.jsx';
import { decode, load } from '../utils/storage.js';

export default function Login({ onLogin }) {
  const [f, setF] = useState({ userName: '', password: '' });
  const [err, setErr] = useState(''); const [busy, setBusy] = useState(false);
  const set = k => e => setF({ ...f, [k]: e.target.value });
  async function submit(e) {
    e.preventDefault(); setErr(''); setBusy(true);
    try {
      const { token } = await login(f.userName.trim(), f.password);
      const claims = decode(token);
      const profile = load('chc.profiles', {})[f.userName.trim()] || {};
      onLogin({ token, userName: f.userName.trim(), role: claims.role || profile.role, healthCardNo: claims.healthCardNo || profile.healthCardNo });
    } catch (x) { setErr(x.status === 0 ? x.message : 'Wrong username or password.'); }
    finally { setBusy(false); }
  }
  return (
    <form onSubmit={submit}>
      <Field label="Username"><input value={f.userName} onChange={set('userName')} autoComplete="username" required /></Field>
      <Field label="Password"><input type="password" value={f.password} onChange={set('password')} autoComplete="current-password" required /></Field>
      {err && <p className="err">{err}</p>}
      <button className="primary" disabled={busy}>{busy ? 'Logging in…' : 'Log in'}</button>
    </form>
  );
}
