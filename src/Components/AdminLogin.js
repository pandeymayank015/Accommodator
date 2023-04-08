import React, { useState } from 'react';
import axios from 'axios';

const AdminLogin = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };
    const handleSubmit = (event) => {
        event.preventDefault();

        if (!email.endsWith('@gmail.com')) {
            console.error('Invalid email address.');
            return;
        }

        const url = `http://csci5308vm25.research.cs.dal.ca:8080/Admin/login`;

        axios
            .post(url, {email, password})
            .then((response) => {
                if (response.status === 200) {
                    console.log(response.data);
                    // authentication succeeded, grant access
                    window.location.href = '/Admin';

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
                    </form>
                </div>
            </div>
        </div>
    );
}
export default AdminLogin;