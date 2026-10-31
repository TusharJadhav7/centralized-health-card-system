import { useState, useEffect } from 'react';
import Home from './pages/Home.jsx';
import Login from './pages/Login.jsx';
import SignUp from './pages/SignUp.jsx';
import HealthCard from './pages/HealthCard.jsx';
import Prescribe from './pages/Prescribe.jsx';
import { ROLES, load, save, decode } from './utils/storage.js';

export default function App() {
  const [session, setSession] = useState(() => {
    const s = load('chc.session', null);
    return s && decode(s.token).exp * 1000 > Date.now() ? s : null;
  });
  const [view, setView] = useState('home'); // home | login | signup | card | rx
  const [notice, setNotice] = useState('');
  useEffect(() => save('chc.session', session), [session]);

  const logout = () => { setSession(null); setView('home'); };
  const isDoctor = session?.role === 'ROLE_DOCTOR';

  return (
    <>
      <header className="top">
        <strong className="brand" onClick={() => setView(session ? 'card' : 'home')}>Health Card</strong>
        {session?.role ? (
          <>
            <nav>
              <button className={view !== 'rx' ? 'on' : ''} onClick={() => setView('card')}>{session.role === 'ROLE_PATIENT' ? 'My health card' : 'Find a patient'}</button>
              {isDoctor && <button className={view === 'rx' ? 'on' : ''} onClick={() => setView('rx')}>New prescription</button>}
            </nav>
            <span className="who">{session.userName} ({ROLES[session.role]})</span>
            <button className="ghost" onClick={logout}>Log out</button>
          </>
        ) : (
          <>
            <nav />
            <button className="ghost" onClick={() => setView('login')}>Log in</button>
            <button className="primary sm" onClick={() => setView('signup')}>Create account</button>
          </>
        )}
      </header>
      <main>
        {!session && view === 'home' && <Home go={setView} />}
        {!session && (view === 'login' || view === 'signup') && (
          <section className="panel">
            <div className="tabs">
              <button className={view === 'login' ? 'on' : ''} onClick={() => setView('login')}>Log in</button>
              <button className={view === 'signup' ? 'on' : ''} onClick={() => setView('signup')}>Create account</button>
            </div>
            {notice && <p className="ok">{notice}</p>}
            {view === 'login'
              ? <Login onLogin={s => { setSession(s); setView('card'); setNotice(''); }} />
              : <SignUp onDone={() => { setNotice('Account created. Log in to continue.'); setView('login'); }} />}
          </section>
        )}
        {session && !session.role && (
          <section className="panel">
            <h2>How will you use Health Card?</h2>
            <p>We could not read your role from your login. Pick one; the server still checks what you are allowed to do.</p>
            <div className="seg">{Object.entries(ROLES).map(([k, v]) => <button key={k} onClick={() => setSession({ ...session, role: k })}>{v}</button>)}</div>
          </section>
        )}
        {session?.role && (view === 'rx' && isDoctor ? <Prescribe session={session} /> : <HealthCard session={session} />)}
      </main>
    </>
  );
}
