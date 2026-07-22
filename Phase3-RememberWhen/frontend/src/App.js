import React, { useState } from 'react';
import './App.css';

function App() {
  const [view, setView] = useState('signup'); // 'signup', 'login', or 'search'
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [birthYear, setBirthYear] = useState('');
  const [country, setCountry] = useState('');
  const [message, setMessage] = useState('');
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);

  const API_BASE = 'http://localhost:8080';

  // Handles both signup and login, since they share the same form shape
  const handleAuth = async (e) => {
    e.preventDefault();
    setMessage('');

    const endpoint = view === 'signup' ? '/api/auth/signup' : '/api/auth/login';
    const body = view === 'signup'
      ? { email, password, birthYear: parseInt(birthYear), country }
      : { email, password };

    try {
      const response = await fetch(API_BASE + endpoint, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
      });

      const data = await response.json();

      if (!response.ok) {
        setMessage(data.error || 'Something went wrong');
        return;
      }

      setMessage(data.message);
      setBirthYear(data.birthYear || birthYear);
      setCountry(data.country || country);
      setView('search'); // move to era search after successful auth

    } catch (err) {
      setMessage('Could not connect to server: ' + err.message);
    }
  };

  // Fetches era items (movies) for the given year/country
  const handleSearch = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage('');

    try {
      const response = await fetch(
        `${API_BASE}/api/era?year=${birthYear}&country=${encodeURIComponent(country)}`
      );
      const data = await response.json();

      if (!response.ok) {
        setMessage(data.error || 'Failed to fetch era data');
        setLoading(false);
        return;
      }

      setResults(data);
    } catch (err) {
      setMessage('Could not connect to server: ' + err.message);
    }

    setLoading(false);
  };

  return (
    <div className="App">
      <h1>Remember When</h1>
      <p className="tagline">Rediscover your era</p>

      {(view === 'signup' || view === 'login') && (
        <form onSubmit={handleAuth} className="auth-form">
          <h2>{view === 'signup' ? 'Create Account' : 'Log In'}</h2>

          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          {view === 'signup' && (
            <>
              <input
                type="number"
                placeholder="Birth Year (e.g. 1995)"
                value={birthYear}
                onChange={(e) => setBirthYear(e.target.value)}
                required
              />
              <input
                type="text"
                placeholder="Country (e.g. India)"
                value={country}
                onChange={(e) => setCountry(e.target.value)}
                required
              />
            </>
          )}

          <button type="submit">{view === 'signup' ? 'Sign Up' : 'Log In'}</button>

          <p className="switch-link">
            {view === 'signup' ? 'Already have an account?' : "Don't have an account?"}{' '}
            <span onClick={() => setView(view === 'signup' ? 'login' : 'signup')}>
              {view === 'signup' ? 'Log In' : 'Sign Up'}
            </span>
          </p>
        </form>
      )}

      {view === 'search' && (
        <div>
          <form onSubmit={handleSearch} className="search-form">
            <h2>Find Your Era</h2>
            <input
              type="number"
              placeholder="Birth Year"
              value={birthYear}
              onChange={(e) => setBirthYear(e.target.value)}
              required
            />
            <input
              type="text"
              placeholder="Country"
              value={country}
              onChange={(e) => setCountry(e.target.value)}
              required
            />
            <button type="submit" disabled={loading}>
              {loading ? 'Searching...' : 'Show Me My Era'}
            </button>
          </form>

          <div className="results-grid">
            {results.map((item, index) => (
              <div key={index} className="era-card">
                {item.imageUrl ? (
                  <img src={item.imageUrl} alt={item.title} />
                ) : (
                  <div className="no-image">No Image</div>
                )}
                <h3>{item.title}</h3>
                <p>{item.category} · {item.year}</p>
              </div>
            ))}
          </div>
        </div>
      )}

      {message && <p className="message">{message}</p>}
    </div>
  );
}

export default App;