import React from 'react';
import { Carousel, Card, Button } from 'react-bootstrap';

function HomePage() {
    return (<div>
        <Carousel>
            <Carousel.Item>
                <img
                    className="d-block w-100 custom-img"
                    src="https://img.staticmb.com/mbcontent//images/uploads/2022/12/Most-Beautiful-House-in-the-World.jpg"
                    alt="First slide"
                />
                <Carousel.Caption>
                    <h3>ACCOMMODATOR </h3>
                    <p>Where finding a place to stay is hassle-free</p>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className="d-block w-100 custom-img"
                    src="https://live.staticflickr.com/65535/51894671416_8912a155c7_b.jpg"
                    alt="Second slide"
                />
                <Carousel.Caption>
                    <h3>ACCOMMODATOR</h3>
                    <p>Let us take the stress out of finding your student accommodation</p>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className="d-block w-100 custom-img"
                    src="https://assets.architecturaldesigns.com/plan_assets/334115895/large/95130RW_Render-1_1643839475.jpg"
                    alt="Third slide"
                />
                <Carousel.Caption>
                    <h3>ACCOMMODATOR</h3>
                    <p>Find your perfect home away from home</p>
                </Carousel.Caption>
            </Carousel.Item>
        </Carousel>
        <div className="container my-5">

        <div className="container">
                <div className="row">
                    <div className="col-md-6 mx-auto">
                        <div className="card p-3">
                            <h2 className="text-center text-uppercase text-BLACK mb-4">Beware of scams!</h2>

                        </div>
                    </div>
                </div>
            </div>
        </div>

            <div className="container my-5">
            <div className="container">
                <div className="row">

            <div className="row">
                <div className="col-md-4" >
                    <Card style={{ width: '18rem', height: '450px'}}>
                        <Card.Img variant="top" src="https://www.narcity.com/media-library/a-residential-building-with-balconies-in-montreal.jpg?id=30985641&width=1245&height=700&quality=85&coordinates=0%2C41%2C0%2C41 "
                                  style={{ height: '200px' }}/>
                        <Card.Body style={{ height: '220px' }}>
                            <Card.Title>Rental Scams</Card.Title>
                            <Card.Text style={{height: '100px'}}>
                                Canada's housing market has been rife with problems lately and things don't look good for those looking to rent in the country either.
                            </Card.Text>
                            <Button variant="primary" href={"https://www.narcity.com/rental-scams-on-the-rise-in-canada-signs-you-re-being-conned"}>Read</Button>
                        </Card.Body>
                    </Card>
                </div>
                <div className="col-md-4">
                    <Card style={{ width: '18rem', height: '450px'}}>
                        <Card.Img variant="top" src="https://globalnews.ca/wp-content/uploads/2023/03/Immigrant-rental-scam2.jpg?w=2048"
                                  style={{ height: '200px' }}/>
                        <Card.Body style={{ height: '220px' }}>
                            <Card.Title> Newcomer Scams</Card.Title>
                            <Card.Text style={{height: '100px'}}>
                                Newcomers to Canada ‘most vulnerable’ to rental scams
                            </Card.Text>
                            <Button variant="primary" href={"https://globalnews.ca/news/9532094/newcomer-rental-scam-calgary/"}>Read</Button>
                        </Card.Body>
                    </Card>
                </div>
                <div className="col-md-4">
                    <Card style={{ width: '18rem', height: '450px'}}>
                        <Card.Img variant="top" src="https://www.avail.co/wp-content/uploads/2017/04/4-rental-scams-1024x836.jpg" style={{ height: '200px' }}/>
                        <Card.Body style={{ height: '220px' }}>
                            <Card.Title>Spot a Rental Scam</Card.Title>
                            <Card.Text style={{height: '100px'}}>
                                Which warning signs to watch out for, ways to prevent being scammed, and what to do if you’re a victim of a fraudulent listing.
                                </Card.Text>
                            <Button variant="primary" href={"https://www.avail.co/education/guides/a-tenants-guide-to-finding-an-apartment/how-to-spot-a-rental-scam"}>Read</Button>
                        </Card.Body>
                    </Card>

                </div>
            </div>
        </div>
            </div>
            </div>
        </div>
    );
}
export default HomePage;
