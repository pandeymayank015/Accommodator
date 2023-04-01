import {Card, Col, Container, Row} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import React from "react";

function DisplayStudentsAfterPref() {

    const [posts, setPosts] = React.useState([]);

    React. useEffect(() => {
        const data = JSON.parse(localStorage.getItem('Owner_info'));
        setPosts(data);
    }, []);

    function handleConnectClick(email) {
        console.log('Contact with owner on ', email);
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
                                                <Col key={i}>
                                                    <Card>
                                                        <Card.Body>
                                                            <Card.Title>{post.firstName +" "+ post.lastName}</Card.Title>
                                                          <Card.Text>{post.email}</Card.Text>
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