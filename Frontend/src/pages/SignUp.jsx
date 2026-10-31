import { useState } from 'react';
import { signUp } from '../api';
import Field from '../components/Field.jsx';
import { ROLES, load, save } from '../utils/storage.js';

const EMAIL = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const PASS = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%?&])[A-Za-z\d@$!%?&]{8,}$/;
const len = (v, a, b) => (v.length >= a && v.length <= b ? '' : `Use ${a} to ${b} characters`);

function validate(f) {
  const e = {
    firstName: len(f.firstName, 2, 20), lastName: len(f.lastName, 2, 20), userName: len(f.userName, 2, 20),
    email: EMAIL.test(f.email) ? '' : 'Enter a valid email address',
    contact: /^\d{10,15}$/.test(f.contact) ? '' : 'Enter at least 10 digits',
    password: PASS.test(f.password) ? '' : '8+ characters with upper, lower, a number and one of @ $ ! % ? &',
    dob: f.dob && f.dob <= new Date().toISOString().slice(0, 10) ? '' : 'Pick a date in the past',
    address: len(f.address, 2, 200),
    healthCardNo: /^\d{1,10}$/.test(f.healthCardNo) && Number(f.healthCardNo) <= 2147483647 ? '' : 'Digits only, up to 2147483647',
    doctorRegNo: f.role !== 'ROLE_DOCTOR' || /^\d{1,10}$/.test(f.doctorRegNo) ? '' : 'Enter your registration number',
    chemistRegNo: f.role !== 'ROLE_CHEMIST' || /^\d{1,10}$/.test(f.chemistRegNo) ? '' : 'Enter your registration number',
  };
  Object.keys(e).forEach(k => !e[k] && delete e[k]);
  return e;
}

export default function SignUp({ onDone }) {
  const [f, setF] = useState({ role: 'ROLE_PATIENT', firstName: '', lastName: '', userName: '', email: '', contact: '', password: '', dob: '', gender: 'Male', address: '', healthCardNo: '', doctorRegNo: '', chemistRegNo: '' });
  const [errs, setErrs] = useState({}); const [err, setErr] = useState(''); const [busy, setBusy] = useState(false);
  const set = k => e => setF({ ...f, [k]: e.target.value });
  const inp = (k, label, props = {}) => <Field label={label} error={errs[k]}><input value={f[k]} onChange={set(k)} {...props} /></Field>;
  async function submit(e) {
    e.preventDefault(); setErr('');
    const v = validate(f); setErrs(v);
    if (Object.keys(v).length) return;
    setBusy(true);
    try {
      // Backend needs both registration numbers on every account, so non-applicable ones go as 0.
      await signUp({ ...f, healthCardNo: Number(f.healthCardNo), doctorRegNo: Number(f.doctorRegNo || 0), chemistRegNo: Number(f.chemistRegNo || 0) });
      save('chc.profiles', { ...load('chc.profiles', {}), [f.userName]: { role: f.role, healthCardNo: Number(f.healthCardNo) } });
      onDone();
    } catch (x) { setErr(x.message); } finally { setBusy(false); }
  }
  return (
    <form onSubmit={submit} noValidate>
      <div className="seg" role="radiogroup" aria-label="I am a">
        {Object.entries(ROLES).map(([k, v]) => <button type="button" key={k} className={f.role === k ? 'on' : ''} onClick={() => setF({ ...f, role: k })}>{v}</button>)}
      </div>
      <div className="grid2">
        {inp('firstName', 'First name')}{inp('lastName', 'Last name')}
        {inp('userName', 'Username', { autoComplete: 'username' })}{inp('email', 'Email', { type: 'email' })}
        {inp('contact', 'Mobile number', { inputMode: 'numeric' })}{inp('dob', 'Date of birth', { type: 'date' })}
        <Field label="Gender"><select value={f.gender} onChange={set('gender')}><option>Male</option><option>Female</option><option>Other</option></select></Field>
        {inp('healthCardNo', 'Health card number', { inputMode: 'numeric' })}
        {f.role === 'ROLE_DOCTOR' && inp('doctorRegNo', 'Doctor registration number', { inputMode: 'numeric' })}
        {f.role === 'ROLE_CHEMIST' && inp('chemistRegNo', 'Chemist registration number', { inputMode: 'numeric' })}
      </div>
      {inp('address', 'Address')}
      {inp('password', 'Password', { type: 'password', autoComplete: 'new-password' })}
      {err && <p className="err">{err}</p>}
      <button className="primary" disabled={busy}>{busy ? 'Creating account…' : 'Create account'}</button>
    </form>
  );
}
