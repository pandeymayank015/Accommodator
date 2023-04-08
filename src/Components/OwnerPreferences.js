import React, {useState} from 'react';
import Select from 'react-select';
import axios from 'axios';
import './Preferences.css';
import {Button, Card, Col, Container, Row} from "react-bootstrap";
import apiClient from './apiClient';

import { useNavigate } from 'react-router-dom';

const options = [{value: 'Dalhousie University', label: 'Dalhousie University'}, {
    value: 'Saint Marys University', label: 'Saint Marys University'
}, {
    value: 'Mount Saint Vincent University', label: 'Mount Saint Vincent University'
}, {
    value: 'University of Kings College', label: 'University of Kings College'
}, {
    value: 'NSCAD University (Nova Scotia College of Art and Design)',
    label: 'NSCAD University (Nova Scotia College of Art and Design)'
}, {
    value: 'Atlantic School of Theology', label: 'Atlantic School of Theology'
}, {
    value: 'Maritime Conservatory of Performing Arts', label: 'Maritime Conservatory of Performing Arts'
}, {
    value: 'Canadian College of Acupuncture and Traditional Chinese Medicine',
    label: 'Canadian College of Acupuncture and Traditional Chinese Medicine'
}, {value: 'Eastern College', label: 'Eastern College'}, {
    value: 'Centre for Arts and Technology - Halifax', label: 'Centre for Arts and Technology - Halifax'
}, {
    value: 'Academy of Cosmetology and Massage', label: 'Academy of Cosmetology and Massage'
}, {value: 'Robertson College', label: 'Robertson College'}, {
    value: 'Centre for Distance Education', label: 'Centre for Distance Education'
}];
const foodOptions = [{value: 'Vegetarian', label: 'Vegetarian'}, {
    value: 'Eggetarian', label: 'Eggetarian'
}, {value: 'Non-vegetarian', label: 'Non-vegetarian'},];
const smokingOptions = [{value: 'Yes', label: 'Yes'}, {value: 'No', label: 'No'},];
const drinkOptions = [{value: 'Yes', label: 'Yes'}, {value: 'No', label: 'No'},];
const livingOptions = [{value: 'a very clean space', label: 'a very clean space'}, {
    value: 'a messy space', label: 'a messy space'
}, {
    value: 'I don’t care what condition the apartment/room is in',
    label: 'I don’t care what condition the apartment/room is in'
},];
const studyOptions = [{
    value: 'I need to study in a very quiet environment', label: 'I need to study in a very quiet environment'
}, {
    value: 'My ideal study environment has some background noise',
    label: 'My ideal study environment has some background noise'
}, {
    value: 'High noise levels do not disturb me while studying',
    label: 'High noise levels do not disturb me while studying'
},];

const interestOptions = [{value: 'Gym', label: 'Gym'}, {value: 'Music', label: 'Music'}, {
    value: 'Sports', label: 'Sports'
}, {value: 'Travel', label: 'Travel'}, {value: 'Photography', label: 'Photography'}, {
    value: 'Reading', label: 'Reading'
}, ,];


const nationalityOptions = [{value: 'South Asian', label: 'South Asian'},
    {value: 'Arab', label: 'Arab'},
    {value: 'Latin American', label: 'Latin American'},
    {value: 'Korean', label: 'Korean'},
    {value: 'Japanese', label: 'Japanese'},
    { value: 'Chinese',  label: 'Chinese'},
    { value: 'Filipino', label: 'Filipino'},
    { value: 'South East Asian', label: 'South East Asian'},
    { value: 'American', label: 'American'},
    { value: 'European', label: 'European'},];

function OwnerPreferences() {
    const changePage=useNavigate();
    const [selectedOption1, setSelectedOption1] = useState(null);
    const [selectedOption2, setSelectedOption2] = useState(null);
    const [selectedOption3, setSelectedOption3] = useState(null);
    const [selectedOption4, setSelectedOption4] = useState(null);
    const [selectedOption5, setSelectedOption5] = useState(null);
    const [selectedOption6, setSelectedOption6] = useState(null);
    const [selectedOption7, setSelectedOption7] = useState(null);
    const [multiSelectedOptions, setMultiSelectedOptions] = useState([]);
    const handleSingleSelectChange1 = (option) => {
        setSelectedOption1(option);
    };
    const handleSingleSelectChange2 = (option) => {
        setSelectedOption2(option);
    };
    const handleSingleSelectChange3 = (option) => {
        setSelectedOption3(option);
    };
    const handleSingleSelectChange4 = (option) => {
        setSelectedOption4(option);
    };
    const handleSingleSelectChange5 = (option) => {
        setSelectedOption5(option);
    };
    const handleSingleSelectChange6 = (option) => {
        setSelectedOption6(option);
    };
    const handleSingleSelectChange7 = (option) => {
        setSelectedOption7(option);
    };
    const handleMultiSelectChange = (options) => {
        setMultiSelectedOptions(options);
    };
    // const handleSubmit = (event) => {
    //     event.preventDefault();
    //     const formData = new FormData();
    //      formData.append('university', selectedOption1.value);
    //      formData.append('foodPreference', selectedOption2.value);
    //      formData.append('isSmoking',selectedOption3.value);
    //      formData.append('isDrinking', selectedOption4.value);
    //      formData.append('livingSpace', selectedOption5.value);
    //      formData.append('studyEnvironment', selectedOption6.value);
    //      formData.append('nationality', selectedOption7.value);

    //      const authToken = localStorage.getItem('authToken');
    //      const queryParams = `?authToken=${authToken}`;
    //      apiClient.post('http://csci5308vm25.research.cs.dal.ca:8080/ownerpref/match${queryParams}',formData)
    //       .then(response => {
    //         console.log(response.data);
    //           localStorage.setItem("Owner_info",JSON.stringify(response.data));
    //           window.location.href = '/DisplayStudentsAfterPref';
    //       })
    //       .catch(error => {
    //         console.error(error);
    //       });
    //   };
    // const handleSubmit = (event) => {
    //     event.preventDefault();
    //     const formData = new FormData();
    //     formData.append('university', selectedOption1.value);
    //     formData.append('foodPreference', selectedOption2.value);
    //     formData.append('isSmoking', selectedOption3.value);
    //     formData.append('isDrinking', selectedOption4.value);
    //     formData.append('livingSpace', selectedOption5.value);
    //     formData.append('studyEnvironment', selectedOption6.value);
    //     formData.append('nationality', selectedOption7.value);
    
    //     const authToken = localStorage.getItem('authToken');
    //     const headers = {
    //         Authorization: `Bearer ${authToken}`,
    //     };
    
    //     apiClient.post('http://csci5308vm25.research.cs.dal.ca:8080/ownerpref/match', formData, { headers })
    //         .then(response => {
    //             console.log(response.data);
    //             localStorage.setItem("Owner_info", JSON.stringify(response.data));
    //             window.location.href = '/DisplayStudentsAfterPref';
    //         })
    //         .catch(error => {
    //             console.error(error);
    //         });
    // };

    const handleSubmit = (event) => {
        event.preventDefault();
        
        
        const queryParams = new URLSearchParams({
            university: selectedOption1.value,
            foodPreference: selectedOption2.value,
            isSmoking: selectedOption3.value,
            isDrinking: selectedOption4.value,
            livingSpace: selectedOption5.value,
            studyEnvironment: selectedOption6.value,
            nationality: selectedOption7.value,
          });

        const authToken = localStorage.getItem("authToken");
        queryParams.append("authToken", authToken);
        console.log(queryParams.toString)


        apiClient
          .post("http://csci5308vm25.research.cs.dal.ca:8080/ownerpref/match?" + queryParams.toString())
          .then((response) => {
            console.log(response.data);             
            return response;
            
          })
          .then((response)=>{
            changePage('/DisplayStudentsAfterPref', { state: { ownersData: response.data } });
          })
          .catch((error) => {
            console.error(error);
          });
      };
      
    

    const [posts, setPosts] = React.useState([]);

    React.useEffect(() => {
        apiClient.get('http://csci5308vm25.research.cs.dal.ca:8080/posting/get/all', {
            image: posts.image,
            title: posts.title,
            description: posts.description,
            rent: posts.rent,
            address: posts.address,
            pincode: posts.pincode,
            category: posts.category,
            email: posts.email
        })
            .then(response => {
                setPosts(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);

    function handleConnectClick(email) {
        console.log('Contact with owner on ', email);
    }

    return (<div className="auth-wrapper">
        <div className="form-container">
            <div className="auth-inner">

                <h2>Enter your preferences to find an ideal roommate</h2>
                <form className="form"  onSubmit={handleSubmit}>
                    <label htmlFor="single-select-input1">Select University:</label>


                    <Select
                        id="single-select-input1" options={options} value={selectedOption1}
                        onChange={handleSingleSelectChange1}
                        className="react-select-container" classNamePrefix="react-select"/>


                    <label htmlFor="single-select-input7">Select Nationality:</label>


                    <Select
                        id="single-select-input7" options={nationalityOptions} value={selectedOption7}
                        onChange={handleSingleSelectChange7}
                        className="react-select-container" classNamePrefix="react-select"/>


                    <label htmlFor="single-select-input2">Select
                        Food Preference:
                    </label>
                    <Select id="single-select-input2" options={foodOptions} value={selectedOption2}
                            onChange={handleSingleSelectChange2}
                            className="react-select-container"
                            classNamePrefix="react-select"/>
                    <label htmlFor="single-select-input3">Do
                        you mind if your roommate smokes:</label>
                    <Select id="single-select-input3" options={smokingOptions}
                            value={selectedOption3}
                            onChange={handleSingleSelectChange3}
                            className="react-select-container"
                            classNamePrefix="react-select"/>
                    <label
                        htmlFor="single-select-input4">Do you mind if your roommate drinks:</label>
                    <Select
                        id="single-select-input4" options={drinkOptions} value={selectedOption4}
                        onChange={handleSingleSelectChange4} className="react-select-container"
                        classNamePrefix="react-select"/>

                    <label
                        htmlFor="single-select-input5">Which statement accurately describes your ideal study
                        environment?</label>
                    <Select
                        id="single-select-input5" options={studyOptions} value={selectedOption5}
                        onChange={handleSingleSelectChange5} className="react-select-container"
                        classNamePrefix="react-select"/>

                    <label
                        htmlFor="single-select-input6">I prefer to live in:</label>
                    <Select
                        id="single-select-input6" options={livingOptions} value={selectedOption6}
                        onChange={handleSingleSelectChange6} className="react-select-container"
                        classNamePrefix="react-select"/>


                    <label htmlFor="multi-select-input">Select your interests:</label> <Select
                    id="multi-select-input"
                    options={interestOptions} isMulti
                    value={multiSelectedOptions}
                    onChange={handleMultiSelectChange}
                    className="react-select-container"
                    classNamePrefix="react-select"/>
                    <button className="submit-button">Submit</button>
                </form>
            </div>
        </div>


    </div>
    );
                }

export default OwnerPreferences;

