import hero from '../assets/hero.svg';
import rx from '../assets/rx.svg';
import search from '../assets/search.svg';

export default function Home({ go }) {
  return (
    <>
      <section className="hero">
        <div>
          <h1>Your health history, on one card.</h1>
          <p>Patients see every prescription in one place. Doctors add a new one in a minute.</p>
          <div className="cta">
            <button className="primary" onClick={() => go('signup')}>Create account</button>
            <button className="ghost" onClick={() => go('login')}>Log in</button>
          </div>
        </div>
        <img src={hero} alt="Health card with a heartbeat line" />
      </section>
      <section className="features">
        <article><img src={search} alt="" /><h3>Find a card fast</h3><p>Enter a health card number to see the patient's details and past prescriptions.</p></article>
        <article><img src={rx} alt="" /><h3>Write prescriptions</h3><p>Doctors add medicines, days and morning, afternoon or night doses.</p></article>
      </section>
    </>
  );
}
