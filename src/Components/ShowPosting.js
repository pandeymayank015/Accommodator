import React from 'react';
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import axios from 'axios';
import apiClient from './apiClient';
import { useState } from 'react';
import { FiHeart } from 'react-icons/fi';
function ShowPosting() {




    const [posts, setPosts] = React.useState([]);
    const [originalPosts, setOriginalPosts] = React.useState([]);
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
                setOriginalPosts(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);



  function addFavorites(postId) {
    const user = JSON.parse(localStorage.getItem('user'));
         apiClient.post(`http://csci5308vm25.research.cs.dal.ca:8080/favorite/create`,{studentId: user.id,postId: postId})
  }

  function removeFavorites(postId) {
    const user = JSON.parse(localStorage.getItem('user'));

  }

    function addFavorites(postId,index) {
         const newPosts = [...posts];
         newPosts[index] = {
           ...newPosts[index],
           isFavorite: !newPosts[index].isFavorite
         };
         setPosts(newPosts);
         if (newPosts[index].isFavorite) {
           const user = JSON.parse(localStorage.getItem('user'));
                  apiClient.post(`http://csci5308vm25.research.cs.dal.ca:8080/favorite/create`,{studentId: user.id,postId: postId})
         } else {
          // removeFavorites(postId);
         }


    }


    function handleConnectClick(email) {
        console.log('Contact with owner on ', email);
        const subject = "Request for Accommodation";
        const body = "I am interested in your posting posted in the Accommodator platform. Is it still available?";
        const mailtoUrl = `mailto:${email}?subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`;
        window.location.href = mailtoUrl;

    }

    function handleFilterSubmit(event) {
        event.preventDefault();
        const rent = event.target.rent.value;
        const category = event.target.category.value;
        const zip = event.target.zip.value;
    
        const filteredPosts = originalPosts.filter(post => {
          // Filter by rent
          if (rent && Number(post.rent) > Number(rent)) {
            return false;
          }
    
          // Filter by category
          if (category && post.category !== category) {
            return false;
          } else if (category !== "" && post.category === "") {
            return false;
          }
    
          // Filter by zip
          if (zip && post.pincode !== zip) {
            return false;
          } else if (zip !== "" && post.pincode === "") {
            return false;
          }
    
          return true;
        });
    
        setPosts(filteredPosts);
        console.log(filteredPosts);
      }
    
    
    return (
        <div className="auth-wrapper">
            <div className="form-container">
            <div className="mt-5">
            <div className="row">
                <div className="col-md-3">
                    <div className="card p-3">
                        <h2 className="text-center text-uppercase text-BLACK mb-4">Filter Properties</h2>
                        <form onSubmit={handleFilterSubmit}>
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
                                    <option value="lease">Lease</option>
                                    <option value="roommate">Roommate</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label htmlFor="zip">Preffered zip code:</label>
                                <input type="text" className="form-control" id="zip" name="zip" />
                            </div>
                            <button type="submit"  className="btn btn-primary">Filter</button>
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
                                            <Col key={i} xs={12} md={4}>
                                                <Card style={{ height: "100%" }}>
                                                    <button
                                                        className={
                                                            isCardFavorite ? "favorite-button active" : "favorite-button"
                                                        }
                                                        onClick={() => addFavorites(post.postId, i)}
                                                    >
                                                        <FiHeart
                                                            className={isCardFavorite ? "icon active" : "icon"}
                                                        />
                                                    </button>
                                                    <Card.Img
                                                        variant="top"
                                                        src={`data:image/jpeg;base64,${post.image}`}
                                                        style={{ height: "200px", objectFit: "cover" }}
                                                    />
                                                    <Card.Body>
                                                        <Card.Title>{post.title}</Card.Title>
                                                        <Card.Text className="card-text-height">{post.description}</Card.Text>
                                                        <Card.Text className="card-text-height">Rent: {post.rent}</Card.Text>
                                                        <Card.Text className="card-text-height">
                                                            Address: {post.address}, {post.pincode}
                                                        </Card.Text>
                                                        <Card.Text className="card-text-height">Category: {post.category}</Card.Text>
                                                        <Button
                                                            variant="primary"
                                                            onClick={() => handleConnectClick(post.email)}
                                                        >
                                                            Connect with owner
                                                        </Button>
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