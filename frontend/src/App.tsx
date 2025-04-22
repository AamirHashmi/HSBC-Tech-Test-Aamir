import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

// Fallback mock data to use when backend is unavailable
const MOCK_CITY_DATA = {
  'y': 1, // "Yafran"
  'z': 3, // "Zuwarah", "Zawiya", "Zlitan"
  'a': 0,
  'b': 0,
  'c': 0,
  'd': 0,
  'e': 0,
  'f': 0,
  'g': 0,
  'h': 0,
  'i': 0,
  'j': 0,
  'k': 0,
  'l': 0,
  'm': 0,
  'n': 0,
  'o': 0,
  'p': 0,
  'q': 0,
  'r': 0,
  's': 0,
  't': 0,
  'u': 0,
  'v': 0,
  'w': 0,
  'x': 0
};

function App() {
  const [letter, setLetter] = useState<string>('');
  const [count, setCount] = useState<number | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const [usingMockData, setUsingMockData] = useState<boolean>(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!letter.trim()) {
      setError('Please enter a letter');
      return;
    }
    
    // Only take the first character if user enters more
    const singleLetter = letter.trim().charAt(0).toLowerCase();
    
    // Update the displayed letter to match what we're searching for
    setLetter(singleLetter);
    setLoading(true);
    setError(null);
    setUsingMockData(false);
    
    try {
      const response = await axios.get(`http://localhost:8082/api/cities/count?letter=${singleLetter}`);
      setCount(response.data.count);
    } catch (err) {
      console.error('Error fetching data from backend:', err);
      
      // Fallback to mock data
      const mockCount = MOCK_CITY_DATA[singleLetter as keyof typeof MOCK_CITY_DATA] || 0;
      setCount(mockCount);
      setUsingMockData(true);
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (letter: string) => {
    setLetter(letter);
    setCount(null);
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>City Weather Search</h1>
      </header>
      <main>
        <div className="search-container">
          <h2>Find cities by first letter</h2>
          <p>Enter a letter to see how many cities in the database start with that letter.</p>
          
          <form onSubmit={handleSubmit}>
            <div className="input-group">
              <input
                type="text"
                value={letter}
                onChange={(e) => handleInputChange(e.target.value)}
                placeholder="Enter a letter"
                maxLength={1}
                aria-label="City first letter"
              />
              <button type="submit" disabled={loading}>
                {loading ? 'Searching...' : 'Search'}
              </button>
            </div>
            {error && <p className="error">{error}</p>}
          </form>
          
          {count !== null && !error && (
            <div className="result">
              <p>
                Found <span className="count">{count}</span> cities starting with the letter '{letter}'.
              </p>
              {usingMockData && (
                <p className="mock-data-note">
                  <small>Note: Using sample data as backend connection failed.</small>
                </p>
              )}
            </div>
          )}
        </div>
      </main>
      <footer>
        <p>HSBC Technical Test</p>
      </footer>
    </div>
  );
}

export default App;
