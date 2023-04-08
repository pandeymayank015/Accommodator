// src/apiClient.js

import axios from 'axios';

const getAuthToken = () => localStorage.getItem('authToken');

const apiClient = axios.create({
  baseURL: 'http://csci5308vm25.research.cs.dal.ca:8080',
  headers: {
    'Content-Type': 'application/json',
  },
});

apiClient.interceptors.request.use((config) => {
  const token = getAuthToken();
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
});

apiClient.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response.status === 401) {
      // Handle token expiration here (e.g., redirect to login page)
      localStorage.removeItem('authToken');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default apiClient;
