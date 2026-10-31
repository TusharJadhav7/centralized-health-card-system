import { useState, useEffect } from 'react';
import { getPatientData } from '../api';
import Field from '../components/Field.jsx';
import Banner from '../components/Banner.jsx';
import search from '../assets/search.svg';
import { dash } from '../utils/storage.js';

export default function HealthCard({ session }) {
  const [no, setNo] = useState(session.healthCardNo ?? '');
  const [data, setData] = useState(null); const [err, setErr] = useState(''); const [busy, setBusy] = useState(false);
  async function go(e) {
    e?.preventDefault(); setErr(''); setData(null); setBusy(true);
    try { setData(await getPatientData(no)); } catch (x) { setErr(x.message); } finally { setBusy(false); }
  }
  useEffect(() => { if (no) go(); }, []); // eslint-disable-line

  const p = data?.patientResponseDTO;
  const recs = data?.medicalRecordResponseDTOList || [];
  return (
    <>
      <Banner image={search} title={session.role === 'ROLE_PATIENT' ? 'My health card' : 'Find a patient'} text="Enter a health card number to see details and prescriptions." />
      <form className="bar" onSubmit={go}>
        <Field label="Health card number"><input value={no} onChange={e => setNo(e.target.value)} inputMode="numeric" required /></Field>
        <button className="primary" disabled={busy}>{busy ? 'Searching…' : 'Show card'}</button>
      </form>
      {err && <p className="err">{err}</p>}
      {p && (
        <section className="idcard">
          <div><small>Health card</small><h2>{p.userName}</h2><p className="no">{p.healthCardNo}</p></div>
          <div className="blood"><b>{dash(p.bloodGroup ?? p.BloodGroup)}</b><small>Blood group</small></div>
          <dl>
            <div><dt>Date of birth</dt><dd>{dash(p.dob ?? p.DOB)}</dd></div>
            <div><dt>Age</dt><dd>{dash(p.age)}</dd></div>
            <div><dt>Weight (kg)</dt><dd>{dash(p.weight)}</dd></div>
            <div><dt>Blood pressure</dt><dd>{dash(p.bloodPressure ?? p.BloodPressure)}</dd></div>
            <div><dt>Gender</dt><dd>{dash(p.gender)}</dd></div>
          </dl>
        </section>
      )}
      {p && <h3 className="sec">Prescriptions</h3>}
      {p && !recs.length && <p className="empty">No prescriptions on this card yet.</p>}
      {recs.map(r => (
        <article className="rec" key={r.medicalRecordId}>
          <header><b>{r.createDate}</b><span>Doctor reg. no. {dash(r.doctorRegNo)}</span></header>
          {(r.medicineInFoResponseDTO || r.medicineInFoEntity || []).map(m => (
            <div className="med" key={m.medicineInfoId}>
              <div><b>{m.medicineName}</b><p>{m.remark}</p></div>
              <span className="days">{m.days} days</span>
              <span className="doses">
                {[['morning', 'Morning'], ['afternoon', 'Afternoon'], ['night', 'Night']].map(([k, l]) =>
                  <i key={k} className={`${k} ${m.doseEntity?.[k] ? 'yes' : ''}`}>{l}</i>)}
              </span>
            </div>
          ))}
        </article>
      ))}
    </>
  );
}
