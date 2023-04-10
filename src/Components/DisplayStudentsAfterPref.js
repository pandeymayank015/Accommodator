import {Card, Col, Container, Row} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import React from "react";
import { useLocation } from "react-router-dom";

function DisplayStudentsAfterPref() {

    const [posts, setPosts] = React.useState([]);

    const location = useLocation();
  
    React. useEffect(() => {
       // const data = JSON.parse(localStorage.getItem('Owner_info'));
       const ownersData = location.state?.ownersData || [];
       setPosts(ownersData);
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

        <h2 className="text-center text-uppercase text-BLACK mb-4">Latest Postings</h2>

        <Container>
            {posts.map((post, index) => {
                if (index % 3 === 0) {
                    const cards = [];
                    for (let i = index; i < index + 3 && i < posts.length; i++) {
                        const post = posts[i];
                        cards.push(
                            <Col key={i} xs={12} md={4}>
                                <Card style={{ height: "100%" }}>
                                    <Card.Body>
                                        <Card.Title className="card-text-height">{post.firstName +" "+ post.lastName}</Card.Title>
                                      <Card.Text className="card-text-height">{post.email}</Card.Text>
                                        <Button variant="primary" onClick={() => handleConnectClick(post.email)}>Connect with student</Button>
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

    );
}

export default DisplayStudentsAfterPref;