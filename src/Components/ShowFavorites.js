import React from 'react';
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import axios from 'axios';
import apiClient from './apiClient';

function ShowFavorites() {

    const [posts, setPosts] = React.useState([]);

    React.useEffect(() => {
        const user = JSON.parse(localStorage.getItem('user'));
        const studentId = user.id;
        apiClient.get(`http://csci5308vm25.research.cs.dal.ca:8080/favorite/get/${studentId}`, {
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
        const subject = "Request for Accommodation";
        const body = "I am interested in your posting posted in the Accommodator platform. Is it still available?";
        const mailtoUrl = `mailto:${email}?subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`;
        window.location.href = mailtoUrl;

    }
    return (
        <div className="auth-wrapper">
            <div className="form-container">
                                <Container>
                                    {posts.map((post, index) => {
                                        if (index % 3 === 0) {
                                            const cards = [];
                                            for (let i = index; i < index + 3 && i < posts.length; i++) {
                                                const post = posts[i];
                                                cards.push(
                                                    <Col key={i} xs={12} md={4}>
                                                        <Card style={{ height: "100%" }}>
                                                            <Card.Img variant="top" src={`data:image/jpeg;base64,${post.image}`} />
                                                            <Card.Body>
                                                                <Card.Title  className="card-text-height">{post.title}</Card.Title>
                                                                <Card.Text className="card-text-height">{post.description}</Card.Text>
                                                                <Card.Text className="card-text-height">Rent: {post.rent}</Card.Text>
                                                                <Card.Text className="card-text-height">Address: {post.address}, {post.pincode}</Card.Text>
                                                                <Card.Text className="card-text-height">Category: {post.category}</Card.Text>
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
    );
}

export default ShowFavorites;