import { useState } from 'react';
import { createMedicalRecord } from '../api';
import Field from '../components/Field.jsx';
import Banner from '../components/Banner.jsx';
import rx from '../assets/rx.svg';

const blank = () => ({ medicineName: '', days: '', remark: '', doseEntity: { morning: false, afternoon: false, night: false } });

export default function Prescribe({ session }) {
  const [f, setF] = useState({ userName: '', healthCardNo: '', doctorRegNo: '' });
  const [meds, setMeds] = useState([blank()]);
  const [err, setErr] = useState(''); const [ok, setOk] = useState(''); const [busy, setBusy] = useState(false);
  const upd = (i, patch) => setMeds(meds.map((m, j) => (j === i ? { ...m, ...patch } : m)));
  const dose = (i, k) => upd(i, { doseEntity: { ...meds[i].doseEntity, [k]: !meds[i].doseEntity[k] } });

  async function submit(e) {
    e.preventDefault(); setErr(''); setOk('');
    const bad = meds.findIndex(m => !m.medicineName.trim() || !(Number(m.days) > 0) || !m.remark.trim() || !Object.values(m.doseEntity).some(Boolean));
    if (bad >= 0) return setErr(`Medicine ${bad + 1}: add a name, number of days, a note, and at least one dose time.`);
    setBusy(true);
    try {
      const res = await createMedicalRecord(session.token, f.userName.trim(), f.healthCardNo, {
        doctorRegNo: Number(f.doctorRegNo),
        medicineInFoEntity: meds.map(m => ({ ...m, days: Number(m.days) })),
      });
      setOk(res?.msg || 'Prescription saved.'); setMeds([blank()]);
    } catch (x) { setErr(x.message); } finally { setBusy(false); }
  }
  return (
    <form className="rx" onSubmit={submit}>
      <Banner image={rx} title="New prescription" text="Add the medicines, how many days, and when to take them." />
      <div className="grid2">
        <Field label="Patient username"><input value={f.userName} onChange={e => setF({ ...f, userName: e.target.value })} required /></Field>
        <Field label="Patient health card number"><input value={f.healthCardNo} onChange={e => setF({ ...f, healthCardNo: e.target.value })} inputMode="numeric" required /></Field>
        <Field label="Your registration number"><input value={f.doctorRegNo} onChange={e => setF({ ...f, doctorRegNo: e.target.value })} inputMode="numeric" required /></Field>
      </div>
      <h3 className="sec">Medicines</h3>
      {meds.map((m, i) => (
        <fieldset key={i}>
          <div className="grid2">
            <Field label="Medicine name"><input value={m.medicineName} onChange={e => upd(i, { medicineName: e.target.value })} /></Field>
            <Field label="Days"><input type="number" min="1" value={m.days} onChange={e => upd(i, { days: e.target.value })} /></Field>
          </div>
          <Field label="Note for the patient"><input value={m.remark} onChange={e => upd(i, { remark: e.target.value })} placeholder="e.g. after food" /></Field>
          <div className="doses pick">
            {[['morning', 'Morning'], ['afternoon', 'Afternoon'], ['night', 'Night']].map(([k, l]) =>
              <button type="button" key={k} className={`${k} ${m.doseEntity[k] ? 'yes' : ''}`} aria-pressed={m.doseEntity[k]} onClick={() => dose(i, k)}>{l}</button>)}
            {meds.length > 1 && <button type="button" className="ghost" onClick={() => setMeds(meds.filter((_, j) => j !== i))}>Remove medicine</button>}
          </div>
        </fieldset>
      ))}
      <button type="button" className="ghost" onClick={() => setMeds([...meds, blank()])}>Add another medicine</button>
      {err && <p className="err">{err}</p>}{ok && <p className="ok">{ok}</p>}
      <button className="primary" disabled={busy}>{busy ? 'Saving…' : 'Save prescription'}</button>
    </form>
  );
}
