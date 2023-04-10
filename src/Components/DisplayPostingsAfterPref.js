import React from 'react';
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import axios from 'axios';

function DisplayPostingsAfterPref() {

    const [posts, setPosts] = React.useState([]);

    React. useEffect(() => {
        const data = JSON.parse(localStorage.getItem('Student_info'));
        setPosts(data);
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
                                            <Card.Img
                                                variant="top"
                                                src={`data:image/jpeg;base64,${post.image}`}
                                                style={{ height: "200px", objectFit: "cover" }}
                                            />
                                            <Card.Body>
                                                <Card.Title className="card-text-height">{post.title}</Card.Title>
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
    );
}

export default DisplayPostingsAfterPref;