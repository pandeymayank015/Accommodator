import React, { useState } from 'react';
import { Form } from 'react-bootstrap';
import axios from 'axios';
import apiClient from './apiClient';

const RegistrationForm = () => {
    const [validated, setValidated] = useState(false);
    const [selectedOption, setSelectedOption] = useState('option1');
    const handleOptionChange = (event) => {
        setSelectedOption(event.target.value);
    };

    const [data, setData] = useState({
        firstname: '',
        lastname: '',
        email: '',
        password: '',
        confirmpassword: '',
        contact: '',
        offerLetter: '',
        ownerType: '',
    });

    const handleOnChange = (e) => {
        const {id, value} = e.target;
        setData((prevState) => ({
            ...prevState,
            [id]: value,
        }));
    };

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        event.preventDefault();

        if (selectedOption === 'option2' && data.ownerType === 'Select type') {
            alert('Please select a type.');
            return;
        }

        if (!data.firstname.match(/^[a-zA-Z]+$/) || data.firstname === '') {
            alert('Enter firstname in only characters.');
            return;
        }
        if (!data.lastname.match(/^[a-zA-Z]+$/) || data.lastname === '') {
            alert('Invalid contact number.Enter 10 digit number.');
            return;
        }

        if (!data.email.endsWith('@dal.ca') || data.email === '') {
            alert('Invalid email address.Enter dal email address.');
            return;
        }

        if (!data.contact.match(/^[0-9]{10}$/) || data.contact === '') {
            alert('Invalid contact number.Enter 10 digit number.');
            return;
        }
        if (data.offerLetter === '' && selectedOption === 'option1') {
            alert('Please upload offer letter.');
            return;
        }

        if (form.checkValidity() === false || data.password.trim() !== data.confirmpassword.trim()) {
            event.stopPropagation();
            alert('Passwords do not match!');
        } else {
            if (selectedOption === 'option1') {
                const formData = new FormData();
formData.append('firstName', data.firstname);
formData.append('lastName', data.lastname);
formData.append('email', data.email);
formData.append('password', data.password);
formData.append('contactNo', data.contact);
const fileInput = document.querySelector('#offerLetter');
const file = fileInput.files[0];
formData.append('offerLetter', file);

axios.post('http://csci5308vm25.research.cs.dal.ca:8080/student/create', formData, {
})
.then((response) => {
    console.log(response.data);
    form.reset();
    localStorage.setItem('authToken', response.data.token);
    localStorage.setItem('user',JSON.stringify(response.data.student));
    alert('Student Successfully registered!');
    window.location.href = '/SetYourPreferences';

})
.catch((error) => {
    console.error('Error:', error);
});

                
            } else {
                            axios.post('http://csci5308vm25.research.cs.dal.ca:8080/owner/create', {
                                firstName: data.firstname,
                                lastName: data.lastname,
                                email: data.email,
                                password: data.password,
                                contactNo: data.contact,
                                ownerType: data.type,
                            })
                                .then((response) => {
                                    console.log(response.data);
                                    form.reset();
                                    localStorage.setItem('authToken', response.data.token);
                                    localStorage.setItem('user',JSON.stringify(response.data.owner));
                                    alert('Owner Successfully registered!');
                                    window.location.href = '/SetPreferencesOwner';
                                })
                                .catch((error) => {
                                    console.error('Error:', error);
                                });
            }
        }
    };
        return (
            <div className="auth-wrapper">
                <div className="form-container">
                    <div className="auth-inner">
            <Form noValidate validated={validated} onSubmit={handleSubmit} enctype="multipart/form-data">
                <Form.Group>
                    <h3>Sign Up</h3>

                    <Form.Check
                        type="radio"
                        label="Student"
                        name="formHorizontalRadios"
                        id="formHorizontalRadios1"
                        value="option1"
                        checked={selectedOption === 'option1'}
                        onChange={handleOptionChange}
                    />
                    <Form.Check
                        type="radio"
                        label="Owner"
                        name="formHorizontalRadios"
                        id="formHorizontalRadios2"
                        value="option2"
                        checked={selectedOption === 'option2'}
                        onChange={handleOptionChange}
                    />
                </Form.Group>

                {selectedOption === 'option1' && (
                    <Form.Group controlId="Registration">
                        <Form.Label>Enter First Name</Form.Label>
                        <Form.Control id="firstname" value={data.firstname} type="text" placeholder="Enter First Name"
                                      onChange={handleOnChange} required/>

                        <Form.Label>Enter Last Name</Form.Label>
                        <Form.Control id="lastname" value={data.lastname} type="text" placeholder="Enter Last Name"
                                      onChange={handleOnChange} required/>

                        <Form.Label>Enter Email address</Form.Label>
                        <Form.Control id="email" value={data.email} type="email" placeholder="Enter email"
                                      pattern=".+@dal.ca" onChange={handleOnChange} required/>
                        <Form.Control.Feedback type="invalid">
                            Please provide a valid email.
                        </Form.Control.Feedback>

                        <Form.Label>Enter Contact No.</Form.Label>
                        <Form.Control id="contact" value={data.contact} type="tel" placeholder="Enter Contact No."
                                      onChange={handleOnChange} required/>
                        <Form.Control.Feedback type="invalid">
                            Please provide a valid contact.
                        </Form.Control.Feedback>

                        <Form.Label>Upload Offer Letter (PDF only)</Form.Label>
                        <Form.Control id="offerLetter" value={data.offerLetter} type="file"
                                      placeholder="Upload Offer Letter" accept="application/pdf" onChange={handleOnChange} required/>


                        <Form.Label>Create password</Form.Label>
                        <Form.Control id="password" value={data.password} type="password" placeholder="Create Password"
                                      onChange={handleOnChange} required/>
                        <Form.Control.Feedback type="invalid">
                            Please provide a valid password.
                        </Form.Control.Feedback>

                        <Form.Label>Re-enter Password</Form.Label>
                        <Form.Control id="confirmpassword" value={data.confirmpassword} type="password"
                                      placeholder="Re-enter Password" onChange={handleOnChange} required/>
                        <Form.Control.Feedback type="invalid">
                            Please provide a valid password.
                        </Form.Control.Feedback>
                    </Form.Group>
                )}
                {selectedOption === 'option2' && (
                    <Form.Group controlId="formBasicUsername">

                        <Form.Label>Enter First Name</Form.Label>
                        <Form.Control id="firstname" value={data.firstname} type="text" placeholder="Enter First Name"
                                      onChange={handleOnChange} required/>

                        <Form.Label>Enter Last Name</Form.Label>
                        <Form.Control id="lastname" value={data.lastname} type="text" placeholder="Enter Last Name"
                                      onChange={handleOnChange} required/>
                        <Form.Label>Owner type? (Lease or Roommate)</Form.Label>
                        <select className="form-select" id="type" value={data.type} aria-label="Select type"
                                onChange={handleOnChange} required>
                            <option>Select type</option>
                            <option value="Lease" >Lease</option>
                            <option value="Roommate">Roommate</option>
                        </select>
                        <Form.Control.Feedback type="invalid">
                            Please Select something.
                        </Form.Control.Feedback>

                        <Form.Label>Enter Email address</Form.Label>
                        <Form.Control id="email" value={data.email} type="email" placeholder="Enter email"
                                      onChange={handleOnChange} required/>
                        <Form.Control.Feedback type="invalid">
                            Please provide a valid email.
                        </Form.Control.Feedback>

                        <Form.Label>Enter Contact No.</Form.Label>
                        <Form.Control id="contact" value={data.contact} type="tel" placeholder="Enter Contact No."
                                      pattern="^[0-9]{10}$" onChange={handleOnChange} required/>
                        <Form.Control.Feedback type="invalid">
                            Please provide a valid contact.
                        </Form.Control.Feedback>

                        <Form.Label>Create password</Form.Label>
                        <Form.Control id="password" value={data.password} type="password" placeholder="Create Password"
                                      onChange={handleOnChange} required/>
                        <Form.Control.Feedback type="invalid">
                            Please provide a valid password.
                        </Form.Control.Feedback>

                        <Form.Label>Re-enter Password</Form.Label>
                        <Form.Control id="confirmpassword" type="password" value={data.confirmpassword}
                                      placeholder="Re-enter Password" onChange={handleOnChange} required/>
                        <Form.Control.Feedback type="invalid">
                            Please provide a valid password.
                        </Form.Control.Feedback>
                    </Form.Group>
                )}

                <br/>
                <div className="d-grid">
                    {/*{!passwordsMatch && <p>Passwords do not match.</p>}*/}
                    <button type="submit" className="btn btn-primary">
                        Submit
                    </button>
                </div>
            </Form>
</div>
</div>
</div>
        );
    };
export default RegistrationForm;
