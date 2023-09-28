import React from 'react';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

function Features() {
    const cards = [
        {
            id: 1,
            title: 'Add a post',
            description: 'Click to add posting for the accommodation',
            image:'https://res.cloudinary.com/essential-living/image/upload/ar_1.7772511848341233,c_fill,g_auto,w_1920/f_auto/q_auto/v1/Developments/Union%20Wharf/Apartments/3%20Bed/union-wharf-3-bed-wh03_12?_a=ATO2BAA0',
            style:' width:200px, height:200px }}',
            buttonLabel: 'Click here',
            buttonLink: '/AddPosting',
        },
        {
            id: 2,
            title: 'Roommate Matching',
            description: 'Click to find a roommate that shares your interests.',
            image:'https://www.udr.com/globalassets/corporate/other/roommatefinder/roommatefinder_logo_final.png',
            buttonLabel: 'Click here',
            buttonLink: '/OwnerPreferences',

        },
        {
            id: 3,
            title: 'See Listings',
            description: 'Click to see all the available listings',
            image:'https://cdn.w600.comps.canstockphoto.com/new-listing-stamp-eps-vector_csp32695784.jpg',
            buttonLabel: 'Click here',
            buttonLink: '/ShowOwnerPosting',

        },
    ];

    return (
        <div className="auth-wrapper">
            <div className="form-container">

        <div className="card-deck">
            {cards.map((card) => (
                <Card key={card.id}>
                    <Card.Img variant="top" src={card.image} alt={card.title} style={{ width: '610px', height: '270px' }}/>
                    <Card.Body>
                        <Card.Title>{card.title}</Card.Title>
                        <Card.Text>{card.description}</Card.Text>
                        <Button variant="primary" href={card.buttonLink}>
                            {card.buttonLabel}
                        </Button>
                    </Card.Body>
                </Card>
            ))}
        </div>
        </div>
            </div>


    );
}

export default Features;
