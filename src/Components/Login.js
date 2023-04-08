import React, { useState } from 'react';
import axios from 'axios';
import apiClient from './apiClient';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [userType, setUserType] = useState('option1');

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleUserTypeChange = (event) => {
        setUserType(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (!email.endsWith('@dal.ca')) {
            console.error('Invalid email address.');
            return;
        }

        

        const endpoint = userType === 'option1' ? 'student' : 'owner';
        const url = `http://csci5308vm25.research.cs.dal.ca:8080/${endpoint}/login`;

        axios
            .post(url, { email, password })
            .then((response) => {
                if (response.status === 200) {
                    console.log(response.data);
                    localStorage.setItem('authToken', response.data.token);
                    localStorage.setItem('user', JSON.stringify(response.data.student || response.data.owner));
                    
                    // authentication succeeded, grant access
                    if (userType === 'option1') {
                        window.location.href = '/StudentFeatures';
                    }

                    else{
                        window.location.href = '/OwnerFeatures';
                    }

                } else {
                    // authentication failed, display an error message
                    alert('Invalid email or password');
                    console.error('Authentication failed:', response.data.message);
                }
            })
            .catch((error) => {
                alert('Invalid email or password');
                console.error('An error occurred:', error);
            });
    };

    return (
        <div className="auth-wrapper">
            <div className="form-container">
                <div className="auth-inner">
                    <form onSubmit={handleSubmit}>
                        <h3>Sign In</h3>
                        <div className="mb-3">
                            <label>Email address</label>
                            <input
                                id="email"
                                name="email"
                                type="email"
                                className="form-control"
                                placeholder="Enter email"
                                value={email}
                                onChange={handleEmailChange}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label>Password</label>
                            <input
                                id="password"
                                name="password"
                                type="password"
                                className="form-control"
                                placeholder="Enter password"
                                value={password}
                                onChange={handlePasswordChange}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <input
                                id="Student"
                                name="userType"
                                type="radio"
                                value="option1"
                                checked={userType === 'option1'}
                                onChange={handleUserTypeChange}
                            />
                            <label>Student</label>
                            <input
                                id="Owner"
                                name="userType"
                                type="radio"
                                value="option2"
                                checked={userType === 'option2'}
                                onChange={handleUserTypeChange}
                            />
                            <label>Owner</label>
                        </div>
                        <div className="mb-3">
                            <div className="custom-control custom-checkbox">
                                <input
                                    type="checkbox"
                                    className="custom-control-input"
                                    id="customCheck1"
                                />
                                <label className="custom-control-label" htmlFor="customCheck1">
                                    Remember me
                                </label>
                            </div>
                        </div>
                        <div className="d-grid">
                            <button type="submit" className="btn btn-primary">
                                Submit
                            </button>
                        </div>

                        <p className="forgot-password text-right">
                            Forgot <a href="Posting.js">password?</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    );
};
export default Login;