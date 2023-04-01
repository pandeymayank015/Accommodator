import React from 'react';
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import axios from 'axios';
import apiClient from './apiClient';
import { useState } from 'react';
import { FiHeart } from 'react-icons/fi';
function ShowPosting() {




    const [posts, setPosts] = React.useState([]);

    React.useEffect(() => {
        apiClient.get('http://localhost:8080/posting/get/all', {
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

         function addFavorites(postId) {
              const user = JSON.parse(localStorage.getItem('user'));
              apiClient.post(`http://localhost:8080/favorite/create`,{studentId: user.id,postId: postId})
          }


  function addFavorites(postId) {
    const user = JSON.parse(localStorage.getItem('user'));
         apiClient.post(`http://localhost:8080/favorite/create`,{studentId: user.id,postId: postId})
  }

  function removeFavorites(postId) {
    const user = JSON.parse(localStorage.getItem('user'));

  }

 function handleFavoriteClick(postId, index) {
     const newPosts = [...posts];
     newPosts[index] = {
       ...newPosts[index],
       isFavorite: !newPosts[index].isFavorite
     };
     setPosts(newPosts);
     if (newPosts[index].isFavorite) {
       addFavorites(postId);
     } else {
       removeFavorites(postId);
     }
   }

    function handleConnectClick(email) {
        console.log('Contact with owner on ', email);
    }




    return (
        <div className="auth-wrapper">
            <div className="form-container">
            <div className="mt-5">
            <div className="row">
                <div className="col-md-3">
                    <div className="card p-3">
                        <h2 className="text-center text-uppercase text-BLACK mb-4">Filter Properties</h2>
                        <form>
                            <div className="form-group">
                                <label htmlFor="rent">Rent:</label>
                                <input type="range" min="1" max="10000" className="form-control-range" id="rent" name="rent" onInput={(event) => {document.getElementById("rent-value").innerHTML = event.target.value}} />
                                <div className="d-flex justify-content-between">
                                    <small>$1</small>
                                    <small>$10,000</small>
                                </div>
                                <div id="rent-value"></div>
                            </div>
                            <div className="form-group">
                                <label htmlFor="category">Category:</label>
                                <select className="form-control" id="category" name="category">
                                    <option value="">Select Lease/ Roommate</option>
                                    <option value="Lease">Lease</option>
                                    <option value="Roommate">Roommate</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label htmlFor="zip">Preffered zip code:</label>
                                <input type="text" className="form-control" id="zip" name="zip" />
                            </div>
                            <button type="submit" className="btn btn-primary">Filter</button>
                        </form>

                    </div>
                </div>
                <div className="col-md-9">
                    <div className="card p-3">
                        <h2 className="text-center text-uppercase text-BLACK mb-4">Latest Postings</h2>

                        <Container>
                            {posts.map((post, index) => {
                                if (index % 3 === 0) {
                                    const cards = [];
                                    for (let i = index; i < index + 3 && i < posts.length; i++) {
                                        const post = posts[i];
                                           const isCardFavorite = post.isFavorite || false;
                                        cards.push(
                                            <Col key={i}>
                                                <Card>
                                                 <button className={isCardFavorite ? "favorite-button active" : "favorite-button"} onClick={() => handleFavoriteClick(post.id, i)}>
                                                                                     <FiHeart className={isCardFavorite ? "icon active" : "icon"} />
                                                                                 </button>
                                                    <Card.Img variant="top" src={post.image} />
                                                    <Card.Body>
                                                        <Card.Title>{post.title}</Card.Title>
                                                        <Card.Text>{post.description}</Card.Text>
                                                        <Card.Text>Rent: {post.rent}</Card.Text>
                                                        <Card.Text>Address: {post.address}, {post.pincode}</Card.Text>
                                                        <Card.Text>Category: {post.category}</Card.Text>
                                                        <Button variant="primary" onClick={() => handleConnectClick(post.email)}>Connect with owner</Button>
                                                    </Card.Body>
                                                </Card>
                                            </Col>
                                        );
                                    }
                                    return (
                                        <Row key={index} className="my-3">
                                            {cards}
                                        </Row>
                                    );
                                }
                            })}
                        </Container>
                    </div>
                </div>
            </div>
        </div>
        </div>
        </div>
    );
                }

export default ShowPosting;