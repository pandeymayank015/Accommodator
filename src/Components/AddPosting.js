import React from 'react';
import apiClient from './apiClient';

class AddPost extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            type: '',
            image: [],
            description: '',
            Rent: '',
            address: '',
            pincode: '',
            category: '',
            email: '',
            error: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    validateImages(files) {
        const validExtensions = ['.jpg', '.jpeg', '.png'];
        for (let i = 0; i < files.length; i++) {
            const extension = files[i].name.split('.').pop().toLowerCase();
            if (!validExtensions.includes('.' + extension)) {
                return false;
            }
        }
        return true;
    }

    handleChange(event) {
        const {name, value, files} = event.target;
        if (name === 'image') {
            if (!this.validateImages(files)) {
                console.log('bye')
                this.setState({error: 'Invalid file type. Please select images with .jpg, .jpeg, or .png extensions.'});
            } else {
                console.log('hi');
                this.setState({image: files[0], error: ''});
            }
        } else {
            this.setState({[name]: value});
        }
    }

    handleSubmit(event) {
        event.preventDefault();
      
        const user = JSON.parse(localStorage.getItem('user'));
        const ownerId = user.ownerId;
      
        const { type, description, rent, address, pincode, category, email, image } = this.state;
        const currentDate = new Date();
        const formattedDate = `${currentDate.getFullYear()}-${currentDate.getMonth() + 1}-${currentDate.getDate()}`;
        const formData = new FormData();
        formData.append('type', type);
        formData.append('date',formattedDate)
        formData.append('description', description);
        formData.append('rent', rent);
        formData.append('address', address);
        formData.append('pincode', pincode);
        formData.append('category', category);
        formData.append('email', email);
        formData.append('ownerId', ownerId);
        formData.append('image', image);
      
        apiClient.post('http://csci5308vm25.research.cs.dal.ca:8080/posting/create', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })
          .then((response) => {
            if (response.status === 200) {
              window.location.href = '/OwnerFeatures';
            } else {
              console.error('Post creation failed:', response.data.message);
            }
          })
          .catch((error) => {
            console.error('An error occurred:', error);
          });
      }
      
    render() {
        const { error } = this.state;
        return (
            <div className="auth-wrapper">
                <div className="form-container">
                    <div className="auth-inner">
                        <h2 className="text-center mb-3">
                            Add a Post
                        </h2>
                        <form onSubmit={this.handleSubmit}>
                            <div className="form-group">
                                <label htmlFor="type">
                                    Type
                                </label>
                                <select className="form-control" id="type" name="type" onChange={this.handleChange} required>
                                    <option value="">Select Type</option>
                                    <option value="apartment">Apartment</option>
                                    <option value="condo">Condo</option>
                                    <option value="townhouse">Townhouse</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label htmlFor="image">
                                    Images
                                </label>
                                <input
                                    type="file"
                                    className="form-control-file"
                                    id="image"
                                    name="image"
                                    onChange={this.handleChange}
                                    multiple
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">
                                    Description
                                </label>
                                <textarea
                                    className="form-control"
                                    id="description"
                                    name="description"
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="rent">
                                    Rent
                                </label>
                                <input
                                    type="number"
                                    className="form-control"
                                    id="rent"
                                    name="rent"
                                    onChange={this.handleChange}
                                    maxLength="4"
                                    max="9999"
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="address">
                                    Address
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="address"
                                    name="address"
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="pincode">
                                    Pincode
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="pincode"
                                    name="pincode"
                                    pattern="[a-zA-Z0-9]{3} [a-zA-Z0-9]{3}"
                                    title="Please enter pincode in format 'XXX XXX'"
                                    maxLength="7"
                                    onChange={e => {
                                        const value = e.target.value.toUpperCase();
                                        if (value.length === 3 && !value.includes(' ')) {
                                            this.setState({ pincode: value + ' ' });
                                        } else {
                                            this.setState({ pincode: value });
                                        }
                                    }}
                                    value={this.state.pincode}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="category">
                                    Category
                                </label>
                                <select className="form-control" id="category" name="category" onChange={this.handleChange} required>
                                    <option value="">Select Category</option>
                                    <option value="lease">Lease</option>
                                    <option value="roommate">Roommate</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label htmlFor="email">
                                    Email to be contacted
                                </label>
                                <input
                                    type="email"
                                    className="form-control"
                                    id="email"
                                    name="email"
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            {error && <div className="alert alert-danger">{error}</div>}
                            <div className="form-group mt-3">
                                <button type="submit" className="btn btn-primary btn-block">
                                    Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        );
    }

}
export default AddPost;
