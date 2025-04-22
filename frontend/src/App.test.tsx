import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import axios from 'axios';
import App from './App';

// Mock axios
jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

describe('App', () => {
  beforeEach(() => {
    mockedAxios.get.mockClear();
  });

  test('renders the main heading', () => {
    render(<App />);
    expect(screen.getByText(/City Weather Search/i)).toBeInTheDocument();
  });

  test('shows error when form is submitted without a letter', async () => {
    render(<App />);
    
    // Submit form without entering a letter
    fireEvent.click(screen.getByText('Search'));
    
    // Check if error message is displayed
    expect(screen.getByText('Please enter a letter')).toBeInTheDocument();
  });

  test('shows count when API returns data', async () => {
    // Mock API response
    mockedAxios.get.mockResolvedValueOnce({ 
      data: { count: 3 } 
    });
    
    render(<App />);
    
    // Enter 'z' and submit the form
    fireEvent.change(screen.getByPlaceholderText('Enter a letter'), { target: { value: 'z' } });
    fireEvent.click(screen.getByText('Search'));
    
    // Wait for the results to be displayed
    await waitFor(() => {
      expect(screen.getByText(/Found 3 cities starting with the letter 'z'/i)).toBeInTheDocument();
    });
    
    // Verify that axios was called with the correct URL
    expect(mockedAxios.get).toHaveBeenCalledWith('http://localhost:8080/api/cities/count?letter=z');
  });

  test('shows error when API call fails', async () => {
    // Mock API error
    mockedAxios.get.mockRejectedValueOnce(new Error('API Error'));
    
    render(<App />);
    
    // Enter 'z' and submit the form
    fireEvent.change(screen.getByPlaceholderText('Enter a letter'), { target: { value: 'z' } });
    fireEvent.click(screen.getByText('Search'));
    
    // Wait for the error message to be displayed
    await waitFor(() => {
      expect(screen.getByText('Error fetching data. Please try again.')).toBeInTheDocument();
    });
  });
});