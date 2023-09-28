import { render, screen } from '@testing-library/react';
import App from './App';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import React from 'react';


test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
